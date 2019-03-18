package me.tumur.portfolio.network.repo

import me.tumur.portfolio.database.dao.ProfileDao
import me.tumur.portfolio.database.dao.SocialDao
import me.tumur.portfolio.network.retrofit.ApiPortfolio
import timber.log.Timber

class RepositoryImp constructor(
    private val api: ApiPortfolio,
    private val profileDao: ProfileDao,
    private val socialDao: SocialDao,
    private val tag: String
) : Repository {

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