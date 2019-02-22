package me.tumur.portfolio.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.tumur.portfolio.data.DbConstants

@Entity(tableName = DbConstants.TABLE_PROFILE)
data class ProfileModel(
    @PrimaryKey
    @ColumnInfo(name = DbConstants.PERSON_NAME) var name: String,
    @ColumnInfo(name = DbConstants.PERSON_GREETING) var greeting: String,
    @ColumnInfo(name = DbConstants.PERSON_AVATAR) var avatar: String,
    @ColumnInfo(name = DbConstants.PERSON_TITLE) var title: String,
    @ColumnInfo(name = DbConstants.PERSON_SUMMARY) var summary: String
)