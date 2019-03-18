package me.tumur.portfolio.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import me.tumur.portfolio.database.constants.DbConstants
import me.tumur.portfolio.database.model.SocialModel

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
    abstract fun populateSocial(social: List<SocialModel>?): List<Long>
}