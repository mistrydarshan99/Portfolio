package me.tumur.portfolio.viewmodel.activities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.tumur.portfolio.repository.firebase.RemoteConfig

class MainViewModel(fb: RemoteConfig) : ViewModel() {

    // Remote values for navigation drawer header
    val avatar = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val title = MutableLiveData<String>()

    init {
        avatar.value = fb.getAvatar()
        name.value = fb.getName()
        title.value = fb.getTitle()
    }
}