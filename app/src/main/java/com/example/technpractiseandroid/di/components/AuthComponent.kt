package com.example.technpractiseandroid.di.components

import com.example.technpractiseandroid.auth.login.LoginFragment
import com.example.technpractiseandroid.auth.registration.RegistrationFragment
import com.example.technpractiseandroid.auth.start.StartAuthFragment
import com.example.technpractiseandroid.base.LoginActivity
import com.example.technpractiseandroid.di.ActivityScope
import com.example.technpractiseandroid.di.modules.LoginModule
import com.example.technpractiseandroid.di.modules.RegistrationModule
import dagger.Subcomponent
import dagger.android.support.AndroidSupportInjectionModule

//@Subcomponent(modules = [AndroidSupportInjectionModule::class,LoginModule::class, RegistrationModule::class])
//@ActivityScope
//interface AuthComponent {
//
//    fun inject(fragment: LoginFragment)
//    fun inject(fragment: RegistrationFragment)
//    fun inject(fragment: StartAuthFragment)
//    fun inject(activity: LoginActivity)
//
//    @Subcomponent.Factory
//    interface Factory {
//        // при вызове create() каждый раз будет создаваться новый инстанс данного компонента, и всех его зависимостей.
//        // Поэтому его не нужно нулить, гб почистит старый инстанс
//        fun create(): AuthComponent
//    }
//}