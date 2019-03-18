package me.tumur.portfolio.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import me.tumur.portfolio.database.dao.ProfileDao
import me.tumur.portfolio.database.dao.SocialDao
import me.tumur.portfolio.database.model.ProfileModel
import me.tumur.portfolio.database.model.SocialModel

@Database(version = 7, entities = [ProfileModel::class, SocialModel::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val profileDao: ProfileDao
    abstract val socialDao: SocialDao
}