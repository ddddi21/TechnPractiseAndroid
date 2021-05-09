package com.example.technpractiseandroid.di.components

import android.app.Application
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.auth.login.LoginFragment
import com.example.technpractiseandroid.auth.registration.RegistrationFragment
import com.example.technpractiseandroid.auth.start.StartAuthFragment
import com.example.technpractiseandroid.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


//главный батя по всем модулям

@Component(modules = [AndroidSupportInjectionModule::class, MainActivityModule::class, MainAppModule::class, ViewModelFactoryModule::class,
    FirebaseModule::class]
)
@Singleton
interface MainAppComponent: AndroidInjector<DaggerApplication> {
    fun authComponent(): AuthComponent.Factory


    @Component.Builder
     interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): MainAppComponent
    }

//    fun inject(fragment: LoginFragment)
//    fun inject(fragment: RegistrationFragment)
//    fun inject(fragment: StartAuthFragment)

}