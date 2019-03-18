package me.tumur.portfolio.screens.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.tumur.portfolio.network.repo.Repository
import me.tumur.portfolio.utilities.delegates.Preference

class SplashViewModel(
    private val first: String,
    private val logo: Long,
    private val min: Long,
    private val max: Long,
    private val repo: Repository
) : ViewModel() {

    // Shared preference
    private var isFirstRun by Preference(first, true)

    // Get max or min value for loader animation
    private val duration by lazy { if (isFirstRun) max else min }

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
    private fun delayForAnimation() = viewModelScope.launch {
        delay(logo)                       // Delay for logo animation
        setAnimation(false)               // Show loader animation
        delay(duration)                   // Delay for loader animation
        (isFirstRun).let { false }        // Check shared preference for first run, if true set false
        route.value = true                // Route to MainActivity
    }

    // Show loader animation
    private fun setAnimation(value: Boolean) {
        invisible.value = value
    }

    // Fake Dao invoke for pre-populating database
    private fun checkDbStatus() = viewModelScope.launch(Dispatchers.IO) {
        repo.getProfileRows()
    }
}