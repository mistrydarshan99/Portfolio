package me.tumur.portfolio.viewmodel.activities

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import me.tumur.portfolio.repository.profile.ProfileRepo
import me.tumur.portfolio.utilities.preference.SharedPref
import timber.log.Timber

class SplashViewModel(
    private val max: Int,
    private val min: Int,
    private val pref: SharedPref,
    private val repo: ProfileRepo
) : ViewModel() {

    // Shared preference
    private val firstRun = MutableLiveData<Boolean>().apply { value = pref.getFirstRun() }

    // Coroutines
    private val job = SupervisorJob()
    private val bgScope = CoroutineScope(Dispatchers.IO + job)

    // Icons
    val iconOne = MutableLiveData<Boolean>().apply { value = false }
    private val iconTwo = MutableLiveData<Boolean>().apply { value = false }
    private val icons = MediatorLiveData<Int>().apply { value = 0 }


    // Animation counter
    private val animation = MutableLiveData<Int>().apply { value = 0 }

    init {
        // Initialize shared preference variables
        animation.apply { value = if (firstRun.value!!) max else min }

        // Trigger for pre-populating database
        checkDbStatus()

        // Initialize observer
        icons.addSource(iconOne) { status -> if (status) icons.value = icons.value?.inc() }
        icons.addSource(iconTwo) { status -> if (status) icons.value = icons.value?.inc() }
    }

    // Trigger for pre-populating database
    private fun checkDbStatus() = bgScope.launch {
        val result = repo.getProfileRows()
        Timber.tag("DatabasePop").d(result.toString())

    }

    // Getter
    fun getIconOne() = iconOne.value

    fun getIconTwo() = iconTwo.value
    fun getIcons() = icons
    fun getAnimation() = animation.value

    // Setter
    fun setIconOne(status: Boolean) {
        iconOne.value = status
    }

    fun setIconTwo(status: Boolean) {
        iconTwo.value = status
    }

    fun decAnimation() {
        (animation.value!! > 0).let { animation.value = animation.value?.dec() }
    }

    fun setFirstRun(status: Boolean) {
        firstRun.value = status
        pref.setFirstRun()
    }

}