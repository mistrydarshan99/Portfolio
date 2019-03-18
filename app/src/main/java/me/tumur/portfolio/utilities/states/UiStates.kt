package me.tumur.portfolio.utilities.states

import androidx.annotation.StringRes

/**
 * Base class to represent common UI states
 */
sealed class UIState

/**
 * the screen is currently loading it's data. "loading state"
 */
object Loading : UIState()

/**
 * data was successfully loaded for the screen.  "happy path"
 */
object Success : UIState()

/**
 * the load was successful, but there was no data.  "empty state"
 */
object Empty : UIState()

/**
 * can't connect to the network and database is empty. "no network state"
 */
object NoNetwork : UIState()

/**
 * some type of error occurred and database is empty.  "error state"
 */
class Error(@StringRes val errorMsgId: Int = 0) : UIState()