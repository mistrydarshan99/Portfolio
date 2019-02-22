package me.tumur.portfolio.repository.firebase

interface RemoteConfig {
    fun getName(): String
    fun getAvatar(): String
    fun getTitle(): String
    fun getReadMore(): String
}