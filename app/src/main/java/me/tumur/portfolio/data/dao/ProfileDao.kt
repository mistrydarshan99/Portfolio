package me.tumur.portfolio.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import me.tumur.portfolio.data.DbConstants
import me.tumur.portfolio.data.model.ProfileModel
import me.tumur.portfolio.extentions.getDistinct

@Dao
abstract class ProfileDao : BaseDao<ProfileModel> {

    @Transaction
    open suspend fun updateProfile(profile: ProfileModel) {
        deleteProfile()
        insertProfile(profile)
    }

    @Query(DbConstants.GET_PROFILE)
    protected abstract fun getProfile(): LiveData<ProfileModel>

    // Avoid false positive notifications for observable queries
    fun getDistinctProfile(): LiveData<ProfileModel> = getProfile().getDistinct()

    @Query(DbConstants.DELETE_PROFILE)
    abstract fun deleteProfile()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertProfile(profile: ProfileModel): Long

    @Query(DbConstants.GET_PROFILE_COUNT_ROWS)
    abstract suspend fun getProfileRows(): Int

    // Insert for populating database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun populateProfileList(profile: List<ProfileModel>): List<Long>
}