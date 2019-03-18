package me.tumur.portfolio.screens.profile

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.tumur.portfolio.database.model.ProfileModel
import me.tumur.portfolio.database.model.SocialModel
import me.tumur.portfolio.network.firebase.RemoteConfig
import me.tumur.portfolio.network.repo.Repository
import me.tumur.portfolio.utilities.delegates.Preference
import me.tumur.portfolio.utilities.states.Loading
import me.tumur.portfolio.utilities.states.Success
import me.tumur.portfolio.utilities.states.UIState

class ProfileViewModel(
    context: Context,
    private val repo: Repository,
    fb: RemoteConfig,
    loading: Int,
    loaded: Int,
    fragment: String
) : ViewModel() {

    // Shared preference
    private var lastCache by Preference(fragment, 0)

    // UI State
    private val state = MutableLiveData<Boolean>().apply { false }

    // Data model
    val profile = MutableLiveData<ProfileModel>()
    val social = MutableLiveData<List<SocialModel>>()
    val greeting = MutableLiveData<String>()
    val avatar = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val summary = MutableLiveData<String>()
    val readMore = MutableLiveData<String>()

    // UI state
    val uiState = ObservableField<UIState>(Loading)


    init {


    }

    // Check database
    suspend fun checkDb(): Int {
        return withContext(Dispatchers.IO) { repo.getProfileRows() }
    }

    suspend fun load(): Boolean {
        return false
    }

    // Fetch network data on background thread and save into database
    suspend fun fetch(): Boolean {
        val profile = withContext(Dispatchers.IO) { repo.loadProfileFromNetwork() }
        val social = withContext(Dispatchers.IO) { repo.loadSocialFromNetwork() }
        if (profile && social) {

            // set Cache date in to shared preference

            return true
        }

        return false
    }


    private fun onSuccess() {
        uiState.set(Success)
    }

    private fun onFailure() {

    }
}
