package me.tumur.portfolio.viewmodel.fragments

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.tumur.portfolio.data.model.ProfileModel
import me.tumur.portfolio.data.model.SocialModel
import me.tumur.portfolio.repository.firebase.RemoteConfig
import me.tumur.portfolio.repository.profile.ProfileRepo
import me.tumur.portfolio.utilities.cache.CacheDate

class ProfileViewModel(
    private val cache: CacheDate,
    private val repo: ProfileRepo,
    private val fb: RemoteConfig,
    loading: Int,
    loaded: Int,
    expired: Int,
    private val profileFragment: String
) : ViewModel() {

    // UI profile data
    val profile: LiveData<ProfileModel> by lazy { Transformations.map(repo.getProfile()) { data -> loadProfile(data) } }
    val greeting: LiveData<String>
    val avatar = MutableLiveData<String>()
    val name: LiveData<String>
    val title: LiveData<String>
    val summary: LiveData<String>
    val readMore = MutableLiveData<String>()


    val social: LiveData<List<SocialModel>> by lazy { Transformations.map(repo.getSocial()) { data -> loadSocial(data) } }

    // UI state
    val uiState = MediatorLiveData<Int>()
    val showMessage = MediatorLiveData<Boolean>()
    val isMessageShowed = MutableLiveData<Boolean>()
    val showTest = MediatorLiveData<ProfileModel>()

    // Db request state
    private val isDbLoaded = MediatorLiveData<Boolean>()
    private val dbRequests = MediatorLiveData<Int>()
    private val dbRequest1 = MutableLiveData<Boolean>()
    private val dbRequest2 = MutableLiveData<Boolean>()

    // Network requests sate
    private val isNetworkLoaded = MediatorLiveData<Boolean>()
    private val networkRequests = MediatorLiveData<Int>()
    private val networkRequest1 = MutableLiveData<Boolean>()
    private val networkRequest2 = MutableLiveData<Boolean>()


    init {
        uiState.value = loading

        // Load remote config data
        avatar.value = fb.getAvatar()
        readMore.value = fb.getReadMore()

        // Db requests
        dbRequests.addSource(dbRequest1) { status -> if (status) dbRequests.value!! + 1 }
        dbRequests.addSource(dbRequest2) { status -> if (status) dbRequests.value!! + 1 }
        isDbLoaded.addSource(dbRequests) { status -> if (status >= 2) isDbLoaded.value = true }

        // Network requests
        networkRequests.addSource(networkRequest1) { status -> if (status) networkRequests.value!! + 1 }
        networkRequests.addSource(networkRequest2) { status -> if (status) networkRequests.value!! + 1 }
        isNetworkLoaded.addSource(networkRequests) { status -> if (status >= 2) isNetworkLoaded.value = true }

        // Profile header UI data
        name = Transformations.map(profile) { result -> result.name }
        greeting = Transformations.map(profile) { result -> result.greeting }
        //avatar = Transformations.map(profile) { result -> result.avatar }
        title = Transformations.map(profile) { result -> result.title }
        summary = Transformations.map(profile) { result -> result.summary }

        // Show loaded data
        uiState.addSource(isDbLoaded) { status -> if (status) uiState.value = loaded }

        // Show short message that network data is successfully loaded and saved into database
        showMessage.addSource(isNetworkLoaded) { status -> if (status) showMessage.value = true }

        // Check network and cache date
        val status = cache.getCacheDate(profileFragment)
        when (status) {
            // Fetch network data and save into db
            expired -> loadNetworkData()
        }

        //showTest.addSource(profile) {result -> if(result != null) showTest.value = result.email}
        //showTest.addSource(profile, showTest::setValue)

    }

    // Provide profile data
    private fun loadProfile(profile: ProfileModel): ProfileModel {
        dbRequest1.value = true
        return profile
    }

    // Provide social data
    private fun loadSocial(social: List<SocialModel>): List<SocialModel> {
        dbRequest2.value = true
        return social
    }

    // Fetch network data on background thread and save into database
    private fun loadNetworkData() = viewModelScope.launch {
        val networkFetch1 = withContext(Dispatchers.IO) { repo.loadProfileFromNetwork() }
        val networkFetch2 = withContext(Dispatchers.IO) { repo.loadSocialFromNetwork() }
        if (networkFetch1 && networkFetch2) {
            networkRequest1.value = true
            networkRequest2.value = true
            setCacheDate()
        }
    }

    // Set cache date
    private fun setCacheDate() {
        cache.setCacheDate(profileFragment)
    }
}
