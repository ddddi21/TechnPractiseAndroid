package com.example.technpractiseandroid

import android.app.Application
import com.example.technpractiseandroid.di.components.DaggerMainAppComponent
import com.example.technpractiseandroid.di.components.MainAppComponent

class MyMainApplication: Application() {
    lateinit var mainApplicationInstance: MyMainApplication
    companion object{
        lateinit var appComponent: MainAppComponent
    }


//    @Synchronized
//    fun getInstance(): MyMainApplication {
//        return mainApplicationInstance
//    }

    override fun onCreate() {
        super.onCreate()
        initDagger(this)
//        authComponent()
    }


    fun initDagger(app:Application) {
             appComponent = DaggerMainAppComponent.builder()
                .application(app)
                .build()
    }

//    fun authComponent() = appComponent.authComponent().create()

}