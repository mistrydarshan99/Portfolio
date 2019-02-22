package me.tumur.portfolio.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import me.tumur.portfolio.data.DbConstants
import me.tumur.portfolio.data.model.SocialModel

@Dao
abstract class SocialDao : BaseDao<SocialModel> {

    @Transaction
    open suspend fun updateSocial(social: List<SocialModel>) {
        deleteSocial()
        insertSocial(social)
    }

    @Query(DbConstants.GET_SOCIAL)
    abstract fun getSocial(): LiveData<List<SocialModel>>

    @Query(DbConstants.DELETE_SOCIAL)
    abstract fun deleteSocial()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertSocial(social: List<SocialModel>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun populateSocial(social: List<SocialModel>): List<Long>
}