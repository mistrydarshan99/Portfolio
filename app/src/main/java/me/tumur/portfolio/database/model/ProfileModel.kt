package me.tumur.portfolio.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.tumur.portfolio.database.constants.DbConstants

@Entity(tableName = DbConstants.TABLE_PROFILE)
@JsonClass(generateAdapter = true)
data class ProfileModel(
    @PrimaryKey
    @Json(name = "name") @ColumnInfo(name = DbConstants.PERSON_NAME) var name: String,
    @Json(name = "greeting") @ColumnInfo(name = DbConstants.PERSON_GREETING) var greeting: String,
    @Json(name = "avatar") @ColumnInfo(name = DbConstants.PERSON_AVATAR) var avatar: String,
    @Json(name = "title") @ColumnInfo(name = DbConstants.PERSON_TITLE) var title: String,
    @Json(name = "summary") @ColumnInfo(name = DbConstants.PERSON_SUMMARY) var summary: String
)