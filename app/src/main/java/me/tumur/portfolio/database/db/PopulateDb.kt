package me.tumur.portfolio.database.db

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import me.tumur.portfolio.database.constants.DbConstants
import me.tumur.portfolio.database.dao.ProfileDao
import me.tumur.portfolio.database.dao.SocialDao
import me.tumur.portfolio.database.model.ProfileModel
import me.tumur.portfolio.database.model.SocialModel
import okio.Okio
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import java.util.*

class PopulateDb(ctx: Context, params: WorkerParameters) : Worker(ctx, params), KoinComponent {

    // Inject data access objects for database population
    private val profileDao: ProfileDao by inject()
    private val socialDao: SocialDao by inject()

    /**
     * Workmanager function to populate database from existing json files from assets folder
     */
    override fun doWork(): Result {

        // Parameterized type for JSONAdapter
        val listTypeSocial = Types.newParameterizedType(List::class.java, SocialModel::class.java)

        // Input streams
        val inputStreamProfile = applicationContext.assets.open(DbConstants.DB_PROFILE)
        val inputStreamSocial = applicationContext.assets.open(DbConstants.DB_SOCIAL)

        // Buffers
        val sourceProfile = Okio.source(inputStreamProfile)
        val sourceSocial = Okio.source(inputStreamSocial)

        // Moshi initialization
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .build()

        return try {

            // JsonAdapters
            val adapterProfile = moshi.adapter(ProfileModel::class.java)
            val adapterSocial: JsonAdapter<List<SocialModel>> = moshi.adapter(listTypeSocial)

            // Reading from Json
            val resultProfile = adapterProfile.fromJson(Okio.buffer(sourceProfile))
            val resultSocial = adapterSocial.fromJson(Okio.buffer(sourceSocial))

            //Insert Json objects into database
            profileDao.populateProfileList(resultProfile)
            Timber.tag(DbConstants.TAG).d("Profile json populated")
            socialDao.populateSocial(resultSocial)
            Timber.tag(DbConstants.TAG).d("Social json populated")
            Result.success()
        } catch (ex: Exception) {
            Timber.tag(DbConstants.TAG).e(ex)
            Result.failure()
        } finally {
            sourceProfile.close()
            sourceSocial.close()
        }

    }
}