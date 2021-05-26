package com.example.technpractiseandroid.di.modules

import android.app.Application
import android.content.Context
import com.example.technpractiseandroid.MyMainApplication
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


@Module
class MainAppModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    @Named("IO")
    fun provideCoroutineContext(): CoroutineContext = Dispatchers.IO

//    @Singleton
//    @Provides
//    fun provideGson(): Gson? {
//        return Gson()
//    }
    // add dependency later when i need it

}