package me.tumur.portfolio.repository.profile

import androidx.lifecycle.LiveData
import me.tumur.portfolio.data.model.ProfileModel
import me.tumur.portfolio.data.model.SocialModel

interface ProfileRepo {

    // Get and load profile
    suspend fun getProfileRows(): Int

    fun getProfile(): LiveData<ProfileModel>
    suspend fun loadProfileFromNetwork(): Boolean

    // Get and load social
    fun getSocial(): LiveData<List<SocialModel>>

    suspend fun loadSocialFromNetwork(): Boolean
}