package me.tumur.portfolio.repository.profile

import me.tumur.portfolio.data.dao.ProfileDao
import me.tumur.portfolio.data.dao.SocialDao
import me.tumur.portfolio.repository.retrofit.ApiPortfolio
import timber.log.Timber

class ProfileRepoImp constructor(
    private val api: ApiPortfolio,
    private val profileDao: ProfileDao,
    private val socialDao: SocialDao,
    private val tag: String
) : ProfileRepo {

    // Check Table
    override suspend fun getProfileRows(): Int = profileDao.getProfileRows()

    // Load Profile from database
    override fun getProfile() = profileDao.getDistinctProfile()

    // Load Profile from network and save to database
    override suspend fun loadProfileFromNetwork(): Boolean {
        return try {
            val result = api.getProfile().await()
            when (result.isSuccessful) {
                true -> {
                    profileDao.updateProfile(result.body()!!)
                    true
                }
                false -> {
                    Timber.tag(tag).d(result.errorBody().toString())
                    false
                }
            }
        } catch (exception: Exception) {
            Timber.tag(tag).e(exception.toString())
            return false
        }
    }

    override fun getSocial() = socialDao.getSocial()

    // Load Social from network and save to database
    override suspend fun loadSocialFromNetwork(): Boolean {
        return try {
            val result = api.getSocial().await()
            when (result.isSuccessful) {
                true -> {
                    socialDao.updateSocial(result.body()!!)
                    true
                }
                false -> {
                    Timber.tag(tag).d(result.errorBody().toString())
                    false
                }
            }
        } catch (exception: Exception) {
            Timber.tag(tag).e(exception.toString())
            return false
        }
    }
}