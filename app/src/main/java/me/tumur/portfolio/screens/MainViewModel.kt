package me.tumur.portfolio.screens

import androidx.lifecycle.ViewModel
import me.tumur.portfolio.network.firebase.RemoteConfig

class MainViewModel(
    firebase: RemoteConfig,
    headerName: String,
    headerAvatar: String,
    headerTitle: String,
    screenEmptyTitle: String,
    screenEmptyText: String,
    screenNoNetworkTitle: String,
    screenNoNetworkText: String,
    screenErrorTitle: String,
    screenErrorText: String,
    headerBtnReadMore: String,
    screenBtnTryAgain: String
) : ViewModel() {

    /**
     * Initialize values for navigation drawer header
     */
    val name = firebase.getConfig(headerName)
    val avatar = firebase.getConfig(headerAvatar)
    val title = firebase.getConfig(headerTitle)

    /**
     * Initialize values for different UI states
     */
    val emptyTitle = firebase.getConfig(screenEmptyTitle)
    val emptyText = firebase.getConfig(screenEmptyText)
    val noNetworkTitle = firebase.getConfig(screenNoNetworkTitle)
    val noNetworkText = firebase.getConfig(screenNoNetworkText)
    val errorTitle = firebase.getConfig(screenErrorTitle)
    val errorText = firebase.getConfig(screenErrorText)

    /**
     * Initialize values for buttons
     */
    val btnReadMore = firebase.getConfig(headerBtnReadMore)
    val btnRetry = firebase.getConfig(screenBtnTryAgain)

}