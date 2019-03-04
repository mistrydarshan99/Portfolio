package me.tumur.portfolio.viewmodel.activities

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.tumur.portfolio.coroutines.ScopedViewModel
import me.tumur.portfolio.repository.profile.ProfileRepo
import me.tumur.portfolio.utilities.preference.SharedPref

class SplashViewModel(
    private val logo: Long,
    private val min: Long,
    private val max: Long,
    private val pref: SharedPref,
    private val repo: ProfileRepo
) : ScopedViewModel() {

    // Shared preference
    private val isFirst by lazy { pref.getFirstRun() }

    // Get max or min value for loader animation
    private val duration by lazy { if (isFirst) max else min }

    // State for logo and loader animation
    val invisible = MutableLiveData<Boolean>().apply { value = true }

    // State for routeToMainActivity
    val route = MutableLiveData<Boolean>().apply { value = false }

    init {
        // Pre-populate Db
        checkDbStatus()
        // Delay
        delayForAnimation()
    }

    // Animation management
    private fun delayForAnimation() = uiScope.launch {

        delay(logo)             // Delay for logo animation
        setAnimation(false)     // Show loader animation

        delay(duration)         // Delay for loader animation
        setFirstRun()           // Check shared preference for first run, if true set false
        route.value = true      // Route to MainActivity
    }

    // Show loader animation
    private fun setAnimation(value: Boolean) {
        invisible.value = value
    }

    // Fake Dao invoke for pre-populating database
    private fun checkDbStatus() = bgScope.launch {
        repo.getProfileRows()
    }

    // Set true for first run
    fun setFirstRun() {
        if (isFirst) pref.setFirstRun()
    }

}