package me.tumur.portfolio

import android.app.Application
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import me.tumur.portfolio.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    companion object {

        lateinit var refWatcher: RefWatcher
    }

    override fun onCreate() {
        super.onCreate()

        // Koin
        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()

            // use the Android context given there
            androidContext(this@App)

            // load properties from assets/koin.properties file
            androidFileProperties()

            // module list
            modules(appModule)
        }

        // ThreeTenABP
        AndroidThreeTen.init(this)

        // Stetho
        Stetho.initializeWithDefaults(this)

        // this process is dedicated to leak canary for heap analysis
        if (LeakCanary.isInAnalyzerProcess(this)) return

        // init leak canary
        refWatcher = LeakCanary.install(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}