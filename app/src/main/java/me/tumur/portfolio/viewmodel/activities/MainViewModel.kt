package me.tumur.portfolio.viewmodel.activities

import androidx.lifecycle.ViewModel
import me.tumur.portfolio.extentions.mutableLiveDataOf
import me.tumur.portfolio.repository.firebase.RemoteConfig

class MainViewModel(fb: RemoteConfig) : ViewModel() {

    // Remote values for navigation drawer header
    val avatar = mutableLiveDataOf<String>()
    val name = mutableLiveDataOf<String>()
    val title = mutableLiveDataOf<String>()

    init {
        avatar.value = fb.getAvatar()
        name.value = fb.getName()
        title.value = fb.getTitle()
    }
}