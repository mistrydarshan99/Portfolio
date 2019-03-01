package me.tumur.portfolio.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import me.tumur.portfolio.data.DbConstants
import me.tumur.portfolio.data.dao.ProfileDao
import me.tumur.portfolio.data.dao.SocialDao
import me.tumur.portfolio.data.db.AppDatabase
import me.tumur.portfolio.data.db.PopulateDb
import me.tumur.portfolio.repository.firebase.RemoteConfig
import me.tumur.portfolio.repository.firebase.RemoteConfigImp
import me.tumur.portfolio.repository.profile.ProfileRepo
import me.tumur.portfolio.repository.profile.ProfileRepoImp
import me.tumur.portfolio.repository.retrofit.ApiPortfolio
import me.tumur.portfolio.utilities.cache.CacheDate
import me.tumur.portfolio.utilities.cache.CacheDateImp
import me.tumur.portfolio.utilities.network.NetworkCheck
import me.tumur.portfolio.utilities.network.NetworkCheckImp
import me.tumur.portfolio.utilities.preference.SharedPref
import me.tumur.portfolio.utilities.preference.SharedPrefImp
import me.tumur.portfolio.viewmodel.activities.MainViewModel
import me.tumur.portfolio.viewmodel.activities.SplashViewModel
import me.tumur.portfolio.viewmodel.fragments.ProfileViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModule = module {
    viewModel {
        SplashViewModel(
            getProperty("ANIM_MAX"),
            getProperty("ANIM_MIN"),
            get() as SharedPref,
            get() as ProfileRepo
        )
    }
    viewModel { MainViewModel(get() as RemoteConfig) }
    viewModel {
        ProfileViewModel(
            get() as CacheDate,
            get() as ProfileRepo,
            get() as RemoteConfig,
            getProperty("DB_LOADING"),
            getProperty("DB_LOADED"),
            getProperty("PRF_CACHE_EXPIRED"),
            getProperty("FR_PROFILE")
        )
    }
}

val firebaseModule = module {
    single<RemoteConfig> {
        RemoteConfigImp(
            getProperty("TAG"),
            getProperty("CONF_SUCCESS"),
            getProperty("CONF_FAIL"),
            getProperty("CONF_NAME"),
            getProperty("CONF_AVATAR"),
            getProperty("CONF_TITLE"),
            getProperty("CONF_READ_MORE")
        )
    }
    single { createFirebaseAnalytics(get()) }
}

val utilitiesModule = module {
    single<SharedPref> {
        SharedPrefImp(
            get(),
            getProperty("PRF_NAME"),
            getProperty("PRF_FIRST"),
            getProperty("PRF_PROFILE"),
            getProperty("PRF_PORTFOLIO"),
            getProperty("PRF_EXPERIENCE")
        )
    }
    factory<NetworkCheck> { NetworkCheckImp(get()) }
    single<CacheDate> {
        CacheDateImp(
            get() as SharedPref,
            getProperty("PRF_CACHE_EXPIRED"),
            getProperty("PRF_CACHE_LIMIT"),
            getProperty("FR_PROFILE"),
            getProperty("FR_PORTFOLIO"),
            getProperty("FR_EXPERIENCE")
        )
    }
}

val databaseModule = module {
    single { createAppDatabase(androidApplication()) }
    single { get<AppDatabase>().profileDao }
    single { get<AppDatabase>().socialDao }
}

val networkModule = module {
    single {
        createWebService<ApiPortfolio>(
            getProperty("SERVER_URL"),
            getProperty("HTTP_CONNECT"),
            getProperty("HTTP_READ"),
            getProperty("HTTP_WRITE")
        )
    }
    factory<ProfileRepo> {
        ProfileRepoImp(
            get() as ApiPortfolio,
            get() as ProfileDao,
            get() as SocialDao,
            getProperty("TAG")
        )
    }
}


// Gather all app modules
val appModule = listOf(viewModelModule, firebaseModule, utilitiesModule, databaseModule, networkModule)


// Create OkHttp and Retrofit instances
inline fun <reified T> createWebService(serverUrl: String, connect: Long, read: Long, write: Long): T {
    val retrofit = provideRetrofit(serverUrl, connect, read, write)
    return retrofit.create(T::class.java)
}

fun provideRetrofit(serverUrl: String, connect: Long, read: Long, write: Long): Retrofit {
    return Retrofit.Builder()
        .baseUrl(serverUrl)
        .client(provideOkHttpClient(provideLoggingInterceptor(), connect, read, write))
        .addConverterFactory(GsonConverterFactory.create(provideGsonBuilder()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

fun provideOkHttpClient(interceptor: HttpLoggingInterceptor, connect: Long, read: Long, write: Long): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.connectTimeout(connect, TimeUnit.SECONDS)
    client.readTimeout(read, TimeUnit.SECONDS)
    client.writeTimeout(write, TimeUnit.SECONDS)
    client.addInterceptor(interceptor)
    client.addNetworkInterceptor(StethoInterceptor())
    return client.build()
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

fun provideGsonBuilder(): Gson {
    return GsonBuilder().setDateFormat("dd-MM-yyyy 'at' HH:mm").create()
}

// Create Room Database
internal fun createAppDatabase(context: Context): AppDatabase {

    return Room.databaseBuilder(
        context,
        AppDatabase::class.java, DbConstants.DATABASE_NAME
    )
        //Migration is not provided. So database will be cleared on upgrade
        .fallbackToDestructiveMigration()
        .addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                val request = OneTimeWorkRequestBuilder<PopulateDb>().build()
                WorkManager.getInstance().enqueue(request)
            }
        })
        .build()
}

//Create Firebase Analytics instance
internal fun createFirebaseAnalytics(context: Context): FirebaseAnalytics {
    return FirebaseAnalytics.getInstance(context)
}