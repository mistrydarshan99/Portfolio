package me.tumur.portfolio.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.tumur.portfolio.data.DbConstants

@Entity(tableName = DbConstants.TABLE_SOCIAL)
data class SocialModel(
    @PrimaryKey
    @ColumnInfo(name = DbConstants.SOCIAL_NAME) var name: String,
    @ColumnInfo(name = DbConstants.SOCIAL_URL) var url: String,
    @ColumnInfo(name = DbConstants.SOCIAL_ORDER) var order: Int
)