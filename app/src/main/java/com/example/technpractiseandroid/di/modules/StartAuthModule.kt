package com.example.technpractiseandroid.di.modules

import androidx.lifecycle.ViewModel
import com.example.technpractiseandroid.auth.registration.RegistrationVM
import com.example.technpractiseandroid.auth.start.StartAuthVM
import com.example.technpractiseandroid.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module
interface StartAuthModule {

    @Binds
    @IntoMap
    @ViewModelKey(StartAuthVM::class)
    fun bindRegistrationViewModel(viewModel: StartAuthVM): ViewModel
}