package me.tumur.portfolio.helpers.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPrefImp constructor(
    context: Context,
    private val name: String,
    private val first: String,
    private val profile: String,
    private val portfolio: String,
    private val experience: String
) : SharedPref {

    private var preferences: SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    // First run
    override fun getFirstRun(): Boolean = preferences.getBoolean(first, true)

    override fun setFirstRun() = preferences.edit { putBoolean(first, false) }

    // Profile
    override fun getProfileCacheDate(): Int = preferences.getInt(profile, 0)

    override fun setProfileCacheDate(date: Int) = preferences.edit { putInt(profile, date) }

    // Portfolio
    override fun getPortfolioCacheDate(): Int = preferences.getInt(portfolio, 0)

    override fun setPortfolioCacheDate(date: Int) = preferences.edit { putInt(portfolio, date) }

    // Experience
    override fun getExperienceCacheDate(): Int = preferences.getInt(experience, 0)

    override fun setExperienceCacheDate(date: Int) = preferences.edit { putInt(experience, date) }

}

