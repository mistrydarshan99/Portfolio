package me.tumur.portfolio.network.firebase

interface RemoteConfig {
    fun getConfig(name: String): String
}