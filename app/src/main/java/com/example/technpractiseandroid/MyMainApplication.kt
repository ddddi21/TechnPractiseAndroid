package com.example.technpractiseandroid

import android.app.Application
import com.example.technpractiseandroid.di.components.DaggerMainAppComponent
import com.example.technpractiseandroid.di.components.MainAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyMainApplication: Application() {
    lateinit var mainApplicationInstance: MyMainApplication
    lateinit var appComponent: MainAppComponent


//    @Synchronized
//    fun getInstance(): MyMainApplication {
//        return mainApplicationInstance
//    }

    override fun onCreate() {
        super.onCreate()
        initDagger(this)
    }


    fun initDagger(app:Application) {
             appComponent = DaggerMainAppComponent.builder()
                .application(app)
                .build()
    }
}