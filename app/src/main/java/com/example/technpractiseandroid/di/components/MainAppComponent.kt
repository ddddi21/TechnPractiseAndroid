package com.example.technpractiseandroid.di.components

import android.app.Application
import com.example.technpractiseandroid.auth.login.LoginFragment
import com.example.technpractiseandroid.auth.registration.RegistrationFragment
import com.example.technpractiseandroid.auth.start.StartAuthFragment
import com.example.technpractiseandroid.base.LoginActivity
import com.example.technpractiseandroid.base.MainActivity
import com.example.technpractiseandroid.di.modules.*
import com.example.technpractiseandroid.main.HomeScreenFragment
import com.example.technpractiseandroid.profile.ProfileFragment
import com.example.technpractiseandroid.profile.`fun`.GetFunFragment
import com.example.technpractiseandroid.tasks.AllTasksFragment
import com.example.technpractiseandroid.tasks.CreateTaskFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


//главный батя по всем модулям

@Component(
    modules = [AndroidSupportInjectionModule::class,
        MainActivityModule::class, MainAppModule::class, LoginModule::class, RegistrationModule::class,
        StartAuthModule::class, FirebaseModule::class,
        ViewModelFactoryModule::class, LoginModule::class, RegistrationModule::class,
        StartAuthModule::class, HomeScreenModule::class, ProfileModule::class, CreateTaskModule::class,
        AllTasksModule::class, RepositoriesModule::class, NetModule::class,
        GetFunModule::class]
)
@Singleton
interface MainAppComponent : AndroidInjector<Application> {


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): MainAppComponent
    }

    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegistrationFragment)
    fun inject(fragment: StartAuthFragment)
    fun inject(activity: LoginActivity)
    fun inject(fragment: HomeScreenFragment)
    fun inject(activity: MainActivity)
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: CreateTaskFragment)
    fun inject(fragment: AllTasksFragment)
    fun inject(fragment: GetFunFragment)


}