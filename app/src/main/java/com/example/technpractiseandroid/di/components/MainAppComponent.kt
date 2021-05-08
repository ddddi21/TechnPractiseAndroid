package com.example.technpractiseandroid.di.components

import android.app.Application
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.auth.login.LoginFragment
import com.example.technpractiseandroid.auth.registration.RegistrationFragment
import com.example.technpractiseandroid.di.modules.MainActivityModule
import com.example.technpractiseandroid.di.modules.MainAppModule
import com.example.technpractiseandroid.di.modules.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


//главный батя по всем модулям

@Component(modules = [AndroidSupportInjectionModule::class, MainActivityModule::class, MainAppModule::class, ViewModelFactoryModule::class])
@Singleton
interface MainAppComponent: AndroidInjector<DaggerApplication> {

    @Component.Builder
     interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): MainAppComponent
    }

    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegistrationFragment)

}