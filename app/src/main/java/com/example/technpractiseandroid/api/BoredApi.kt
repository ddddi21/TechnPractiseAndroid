package com.example.technpractiseandroid.api

import com.example.technpractiseandroid.api.response.BoredResponse
import io.reactivex.Single
import retrofit2.http.GET

interface BoredApi {

    @GET("api/activity/")
    fun getRandomActivity() : Single<BoredResponse>
}