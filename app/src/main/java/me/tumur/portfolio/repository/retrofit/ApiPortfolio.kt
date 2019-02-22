package me.tumur.portfolio.repository.retrofit

import kotlinx.coroutines.Deferred
import me.tumur.portfolio.data.model.ProfileModel
import me.tumur.portfolio.data.model.SocialModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiPortfolio {

    @GET("profile/")
    fun getProfile(): Deferred<Response<ProfileModel>>

    @GET("social/")
    fun getSocial(): Deferred<Response<List<SocialModel>>>
}