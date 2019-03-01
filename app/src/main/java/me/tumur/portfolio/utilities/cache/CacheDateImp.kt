package me.tumur.portfolio.utilities.cache

import me.tumur.portfolio.utilities.preference.SharedPref
import org.threeten.bp.LocalDateTime

class CacheDateImp constructor(
    private val pref: SharedPref,
    private val expired: Int,
    private val limit: Int,
    private val profile: String,
    private val portfolio: String,
    private val experience: String
) : CacheDate {

    private val today: Int by lazy {
        LocalDateTime.now().hour
    }

    override fun getCacheDate(name: String): Int {
        val date = checkCache(name)
        val hours = today - date

        return when {
            date == 0 || date > 0 && hours >= limit -> expired
            else -> 0
        }
    }

    override fun setCacheDate(name: String) {
        when (name) {
            profile -> pref.setProfileCacheDate(today)
            portfolio -> pref.setPortfolioCacheDate(today)
            experience -> pref.setExperienceCacheDate(today)
        }
    }

    private fun checkCache(fragmentName: String): Int {
        return when (fragmentName) {
            profile -> pref.getProfileCacheDate()
            portfolio -> pref.getPortfolioCacheDate()
            experience -> pref.getExperienceCacheDate()
            else -> 0
        }
    }


}