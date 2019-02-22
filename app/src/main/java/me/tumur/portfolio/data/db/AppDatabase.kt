package me.tumur.portfolio.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import me.tumur.portfolio.data.dao.ProfileDao
import me.tumur.portfolio.data.dao.SocialDao
import me.tumur.portfolio.data.model.ProfileModel
import me.tumur.portfolio.data.model.SocialModel

@Database(version = 7, entities = [ProfileModel::class, SocialModel::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val profileDao: ProfileDao
    abstract val socialDao: SocialDao
}