package me.tumur.portfolio.network.repo

import androidx.lifecycle.LiveData
import me.tumur.portfolio.database.model.ProfileModel
import me.tumur.portfolio.database.model.SocialModel

interface Repository {

    // Get and load profile
    suspend fun getProfileRows(): Int

    fun getProfile(): LiveData<ProfileModel>
    suspend fun loadProfileFromNetwork(): Boolean

    // Get and load social
    fun getSocial(): LiveData<List<SocialModel>>

    suspend fun loadSocialFromNetwork(): Boolean
}