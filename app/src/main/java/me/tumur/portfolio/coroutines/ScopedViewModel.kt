package me.tumur.portfolio.coroutines

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

open class ScopedViewModel : ViewModel() {

    // Main thread
    private val uiJob = SupervisorJob()
    protected val uiScope = CoroutineScope(Dispatchers.Main + uiJob)

    // Background thread
    private val bgJob = SupervisorJob()
    protected val bgScope = CoroutineScope(Dispatchers.Main + bgJob)

    // Cancel all children
    override fun onCleared() {
        super.onCleared()
        uiScope.coroutineContext.cancelChildren()
        bgScope.coroutineContext.cancelChildren()
    }
}