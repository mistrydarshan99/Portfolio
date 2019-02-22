package me.tumur.portfolio.repository.firebase

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import me.tumur.portfolio.BuildConfig
import me.tumur.portfolio.R
import timber.log.Timber

class RemoteConfigImp constructor(
    private val tag: String,
    private val success: String,
    private val fail: String,
    private val name: String,
    private val avatar: String,
    private val title: String,
    private val readMore: String
) : RemoteConfig {

    // Firebase remote config instance
    private val remote: FirebaseRemoteConfig
        get() = provideFbRemoteConfig()

    override fun getName(): String = remote.getString(name)
    override fun getAvatar(): String = remote.getString(avatar)
    override fun getTitle(): String = remote.getString(title)
    override fun getReadMore(): String = remote.getString(readMore)

    // Provide Firebase remote config instance
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
                Timber.tag(tag).d(fail)
            }
        }
        return remoteConfig
    }
}