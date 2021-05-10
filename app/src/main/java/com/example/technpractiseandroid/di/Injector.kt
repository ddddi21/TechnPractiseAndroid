package com.example.technpractiseandroid.di

import android.app.Application
import com.example.technpractiseandroid.di.components.DaggerMainAppComponent
import com.example.technpractiseandroid.di.components.MainAppComponent

object Injector {

    private lateinit var appComponent: MainAppComponent


    fun init(app: Application) {
        appComponent = DaggerMainAppComponent.builder()
            .application(app)
            .build()
    }


}