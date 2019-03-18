package me.tumur.portfolio.utilities.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.firebase.analytics.FirebaseAnalytics
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import me.tumur.portfolio.database.constants.DbConstants
import me.tumur.portfolio.database.dao.ProfileDao
import me.tumur.portfolio.database.dao.SocialDao
import me.tumur.portfolio.database.db.AppDatabase
import me.tumur.portfolio.database.db.PopulateDb
import me.tumur.portfolio.network.firebase.RemoteConfig
import me.tumur.portfolio.network.firebase.RemoteConfigImp
import me.tumur.portfolio.network.repo.Repository
import me.tumur.portfolio.network.repo.RepositoryImp
import me.tumur.portfolio.network.retrofit.ApiPortfolio
import me.tumur.portfolio.screens.MainViewModel
import me.tumur.portfolio.screens.profile.ProfileViewModel
import me.tumur.portfolio.screens.splash.SplashViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

val viewModelModule = module {
    viewModel {
        SplashViewModel(
            getProperty("PRF_FIRST"),
            getProperty("ANIM_LOGO"),
            getProperty("ANIM_MIN"),
            getProperty("ANIM_MAX"),
            get() as Repository
        )
    }
    viewModel {
        ProfileViewModel(
            get() as Context,
            get() as Repository,
            get() as RemoteConfig,
            getProperty("DB_LOADING"),
            getProperty("DB_LOADED"),
            getProperty("FR_PROFILE")
        )
    }
    viewModel {
        MainViewModel(
            get() as RemoteConfig,
            getProperty("HEADER_NAME"),
            getProperty("HEADER_AVATAR"),
            getProperty("HEADER_TITLE"),
            getProperty("SCREEN_EMPTY_TITLE"),
            getProperty("SCREEN_EMPTY_TEXT"),
            getProperty("SCREEN_NO_NETWORK_TITLE"),
            getProperty("SCREEN_NO_NETWORK_TEXT"),
            getProperty("SCREEN_ERROR_TITLE"),
            getProperty("SCREEN_ERROR_TEXT"),
            getProperty("HEADER_BTN_READ_MORE"),
            getProperty("SCREEN_BTN_TRY_AGAIN")
        )
    }
}

val firebaseModule = module {
    single<RemoteConfig> {
        RemoteConfigImp(
            getProperty("TAG"),
            getProperty("FB_SUCCESS"),
            getProperty("FB_FAIL")
        )
    }
    single { createFirebaseAnalytics(get()) }
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
    factory<Repository> {
        RepositoryImp(
            get() as ApiPortfolio,
            get() as ProfileDao,
            get() as SocialDao,
            getProperty("TAG")
        )
    }
}


// Gather all app modules
val appModule = listOf(viewModelModule, firebaseModule, databaseModule, networkModule)


// Create OkHttp and Retrofit instances
inline fun <reified T> createWebService(serverUrl: String, connect: Long, read: Long, write: Long): T {
    val retrofit = provideRetrofit(serverUrl, connect, read, write)
    return retrofit.create(T::class.java)
}

fun provideRetrofit(serverUrl: String, connect: Long, read: Long, write: Long): Retrofit {
    return Retrofit.Builder()
        .baseUrl(serverUrl)
        .client(provideOkHttpClient(provideLoggingInterceptor(), connect, read, write))
        .addConverterFactory(MoshiConverterFactory.create(provideMoshiBuilder()))
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

fun provideMoshiBuilder(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .build()
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