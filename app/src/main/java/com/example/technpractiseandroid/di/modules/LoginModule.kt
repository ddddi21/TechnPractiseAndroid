package com.example.technpractiseandroid.di.modules

import androidx.lifecycle.ViewModel
import com.example.technpractiseandroid.auth.login.LoginVM
import com.example.technpractiseandroid.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LoginModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginVM::class)
    fun bindLoginViewModel(viewModel: LoginVM): ViewModel
}