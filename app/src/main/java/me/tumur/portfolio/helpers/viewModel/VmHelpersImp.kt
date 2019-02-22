package me.tumur.portfolio.helpers.viewModel

import me.tumur.portfolio.helpers.network.NetworkCheck
import me.tumur.portfolio.helpers.preference.SharedPref
import org.threeten.bp.LocalDateTime

class VmHelpersImp constructor(
    private val sharedPref: SharedPref,
    private val network: NetworkCheck,
    private val cacheExpired: Int,
    private val cacheLimit: Int,
    private val profile: String,
    private val portfolio: String,
    private val experience: String
) : VmHelpers {

    private val connection: Boolean by lazy {
        network.getConnection()
    }

    private val currentDate: Int by lazy {
        LocalDateTime.now().hour
    }

    override fun checkCondition(name: String): Int {
        val cacheDate = provideCacheDate(name)
        val diffHours = currentDate - cacheDate

        return when {
            cacheDate == 0 && connection -> cacheExpired
            cacheDate > 0 && diffHours >= cacheLimit && connection -> cacheExpired
            else -> 0
        }
    }

    override fun setCacheDate(name: String) {
        when (name) {
            profile -> sharedPref.setProfileCacheDate(currentDate)
            portfolio -> sharedPref.setPortfolioCacheDate(currentDate)
            experience -> sharedPref.setExperienceCacheDate(currentDate)
        }
    }

    private fun provideCacheDate(fragmentName: String): Int {
        return when (fragmentName) {
            profile -> sharedPref.getProfileCacheDate()
            portfolio -> sharedPref.getPortfolioCacheDate()
            experience -> sharedPref.getExperienceCacheDate()
            else -> 0
        }
    }


}