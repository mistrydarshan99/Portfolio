package me.tumur.portfolio.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.tumur.portfolio.database.constants.DbConstants

@Entity(tableName = DbConstants.TABLE_SOCIAL)
@JsonClass(generateAdapter = true)
data class SocialModel(
    @PrimaryKey
    @Json(name = "name") @ColumnInfo(name = DbConstants.SOCIAL_NAME) var name: String,
    @Json(name = "url") @ColumnInfo(name = DbConstants.SOCIAL_URL) var url: String,
    @Json(name = "order") @ColumnInfo(name = DbConstants.SOCIAL_ORDER) var order: Int
)