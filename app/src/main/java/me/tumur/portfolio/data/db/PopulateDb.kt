package me.tumur.portfolio.data.db

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import me.tumur.portfolio.data.DbConstants
import me.tumur.portfolio.data.dao.ProfileDao
import me.tumur.portfolio.data.dao.SocialDao
import me.tumur.portfolio.data.model.ProfileModel
import me.tumur.portfolio.data.model.SocialModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class PopulateDb(ctx: Context, params: WorkerParameters) : Worker(ctx, params), KoinComponent {

    private val profileDao: ProfileDao by inject()
    private val socialDao: SocialDao by inject()

    override fun doWork(): Result {
        val profileType = object : TypeToken<List<ProfileModel>>() {}.type
        val socialType = object : TypeToken<List<SocialModel>>() {}.type

        var jsonReaderProfile: JsonReader? = null
        var jsonReaderSocial: JsonReader? = null

        return try {

            //Read Json
            val inputStreamProfile = applicationContext.assets.open(DbConstants.DB_PROFILE)
            val inputStreamSocial = applicationContext.assets.open(DbConstants.DB_SOCIAL)

            jsonReaderProfile = JsonReader(inputStreamProfile.reader())
            jsonReaderSocial = JsonReader(inputStreamSocial.reader())

            //Parse Json
            val profile: List<ProfileModel> = Gson().fromJson(jsonReaderProfile, profileType)
            val social: List<SocialModel> = Gson().fromJson(jsonReaderSocial, socialType)

            //Insert Json objects into database
            profileDao.populateProfileList(profile)
            Timber.tag("DatabasePop").d("Profile json populated")
            socialDao.populateSocial(social)
            Timber.tag("DatabasePop").d("Social json populated")

            Result.success()
        } catch (ex: Exception) {

            Timber.tag(DbConstants.TAG).e(ex)
            Result.failure()

        } finally {
            jsonReaderProfile?.close()
            jsonReaderSocial?.close()
        }

    }
}