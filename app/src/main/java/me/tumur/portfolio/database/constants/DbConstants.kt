package me.tumur.portfolio.database.constants

object DbConstants {

    const val DATABASE_NAME = "portfolio_app.db"
    const val DB_PROFILE = "profile.json"
    const val DB_SOCIAL = "social.json"
    const val TAG = "Populate Database"

    // Database table names
    const val TABLE_PROFILE = "Profile"
    const val TABLE_SOCIAL = "Social"

    // Profile's column names
    const val PERSON_NAME = "PersonName"
    const val PERSON_GREETING = "PersonGreeting"
    const val PERSON_AVATAR = "PersonAvatar"
    const val PERSON_TITLE = "PersonTitle"
    const val PERSON_SUMMARY = "PersonSummary"

    // Social's column names
    const val SOCIAL_NAME = "SocialName"
    const val SOCIAL_URL = "SocialUrl"
    const val SOCIAL_ORDER = "SocialOrder"

    // Queries -> Profile
    const val GET_PROFILE_COUNT_ROWS = "SELECT COUNT(*) FROM Profile"
    const val GET_PROFILE = "SELECT * FROM Profile"
    const val DELETE_PROFILE = "DELETE FROM Profile"

    // Queries -> Social
    const val GET_SOCIAL = "SELECT * FROM Social"
    const val DELETE_SOCIAL = "DELETE FROM Social"


}