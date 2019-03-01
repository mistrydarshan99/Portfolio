package me.tumur.portfolio.utilities.preference

interface SharedPref {

    // First run
    fun getFirstRun(): Boolean

    fun setFirstRun()

    // Profile
    fun getProfileCacheDate(): Int

    fun setProfileCacheDate(date: Int)

    // Portfolio
    fun getPortfolioCacheDate(): Int

    fun setPortfolioCacheDate(date: Int)

    // Experience
    fun getExperienceCacheDate(): Int

    fun setExperienceCacheDate(date: Int)


}