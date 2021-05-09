package com.example.technpractiseandroid.di.modules

import androidx.lifecycle.ViewModel
import com.example.technpractiseandroid.auth.login.LoginVM
import com.example.technpractiseandroid.auth.registration.RegistrationVM
import com.example.technpractiseandroid.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface RegistrationModule {

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationVM::class)
    fun bindRegistrationViewModel(viewModel: RegistrationVM): ViewModel
}