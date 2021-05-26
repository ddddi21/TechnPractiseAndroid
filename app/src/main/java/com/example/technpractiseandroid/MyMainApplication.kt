package com.example.technpractiseandroid

import android.app.Application
import com.example.technpractiseandroid.di.components.DaggerMainAppComponent
import com.example.technpractiseandroid.di.components.MainAppComponent

class MyMainApplication : Application() {

    companion object {
        lateinit var appComponent: MainAppComponent
    }


    override fun onCreate() {
        super.onCreate()
        initDagger(this)
//        authComponent()
    }


    fun initDagger(app: Application) {
        appComponent = DaggerMainAppComponent.builder()
            .application(app)
            .build()
//             authComponent = Dagger
    }

//    fun authComponent() = appComponent.authComponent().create()

}