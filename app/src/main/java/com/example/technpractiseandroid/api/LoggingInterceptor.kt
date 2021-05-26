package com.example.technpractiseandroid.api

import com.example.technpractiseandroid.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Response
class LoggingInterceptor (
        private val loggingInterceptor: HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
            )
    ) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response =
        loggingInterceptor.intercept(chain)
}
