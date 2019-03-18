package me.tumur.portfolio.network.retrofit

import kotlinx.coroutines.Deferred
import me.tumur.portfolio.database.model.ProfileModel
import me.tumur.portfolio.database.model.SocialModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiPortfolio {

    @GET("profile/")
    fun getProfile(): Deferred<Response<ProfileModel>>

    @GET("social/")
    fun getSocial(): Deferred<Response<List<SocialModel>>>
}