package me.tumur.portfolio.utilities.cache

interface CacheDate {

    fun getCacheDate(name: String): Int
    fun setCacheDate(name: String)
}