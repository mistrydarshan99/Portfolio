package me.tumur.portfolio.network.firebase

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import me.tumur.portfolio.BuildConfig
import me.tumur.portfolio.R
import timber.log.Timber

class RemoteConfigImp(
    private val tag: String,
    private val success: String,
    private val failure: String

) : RemoteConfig {


    /**
     * Instance firebase remote config
     */
    private val remote: FirebaseRemoteConfig
        get() = provideFbRemoteConfig()


    /**
     * Get firebase remote config values
     */
    override fun getConfig(name: String): String {
        return remote.getString(name)
    }

    /**
     * Provide instance of firebase remote config, and sync it with firebase server
     */
    private fun provideFbRemoteConfig(): FirebaseRemoteConfig {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings =
            FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(BuildConfig.DEV_ENVIRONMENT).build()
        remoteConfig.setConfigSettings(configSettings)
        remoteConfig.setDefaults(R.xml.remote_config)
        val developerMode = remoteConfig.info.configSettings.isDeveloperModeEnabled
        val cache = if (developerMode) 0L else 3600L

        remoteConfig.fetch(cache).addOnCompleteListener {
            if (it.isSuccessful) {
                Timber.tag(tag).d(success)
                remoteConfig.activateFetched()
            } else {
                Timber.tag(tag).d(failure)
            }
        }
        return remoteConfig
    }
}