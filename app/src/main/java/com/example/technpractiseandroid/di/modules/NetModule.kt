package com.example.technpractiseandroid.di.modules

import com.example.technpractiseandroid.BuildConfig
import com.example.technpractiseandroid.api.BoredApi
import com.example.technpractiseandroid.api.LoggingInterceptor
import dagger.Module
import dagger.Provides
import io.reactivex.SingleConverter
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideBoredApi(retrofit: Retrofit): BoredApi = retrofit.create(BoredApi::class.java)

    @Provides
    @Singleton
    @Named("loggingInterceptor")
    fun provideLoggingInterceptor(): Interceptor = LoggingInterceptor()

    @Provides
    @Singleton
    @Named("apiInterceptor")
    fun provideApiKeyInterceptor(): Interceptor = Interceptor { chain ->
        val original = chain.request()
        original.url().newBuilder()
            .build()
            .let {
                chain.proceed(
                    original.newBuilder().url(it).build()
                )
            }
    }

    @Provides
    @Singleton
    @Named("langInterceptor")
    fun provideLangInterceptor(): Interceptor = Interceptor { chain ->
        val original = chain.request()
        original.url().newBuilder()
            .build()
            .let {
                chain.proceed(
                    original.newBuilder().url(it).build()
                )
            }
    }

    @Provides
    @Singleton
    fun provideClient(
        @Named("loggingInterceptor") loggingInterceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.API_ENDPOINT)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}