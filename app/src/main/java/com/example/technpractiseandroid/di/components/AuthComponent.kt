package com.example.technpractiseandroid.di.components

import android.app.Application
import com.example.technpractiseandroid.auth.login.LoginFragment
import com.example.technpractiseandroid.auth.registration.RegistrationFragment
import com.example.technpractiseandroid.auth.start.StartAuthFragment
import com.example.technpractiseandroid.base.LoginActivity
import com.example.technpractiseandroid.di.ActivityScope
import com.example.technpractiseandroid.di.FragmentScope
import com.example.technpractiseandroid.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
//
//@FragmentScope
//@Subcomponent(modules = [LoginModule::class, RegistrationModule::class,
//    StartAuthModule::class, ViewModelFactoryModule::class, FirebaseModule::class])
//interface AuthComponent {
//
//    fun inject(fragment: LoginFragment)
//    fun inject(fragment: RegistrationFragment)
//    fun inject(fragment: StartAuthFragment)
//    fun inject(activity: LoginActivity)
//
//    @Subcomponent.Builder
//    interface Builder {
//        @BindsInstance
//        fun application(application: Application): Builder
//        fun create(): AuthComponent
//    }
//}
//попытки остались тщетны...