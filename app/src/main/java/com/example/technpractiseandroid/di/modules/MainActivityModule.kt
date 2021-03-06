package com.example.technpractiseandroid.di.modules

import androidx.lifecycle.ViewModel
import com.example.technpractiseandroid.auth.login.LoginVM
import com.example.technpractiseandroid.base.MainActivityVM
import com.example.technpractiseandroid.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module
interface MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityVM::class)
    fun bindLoginViewModel(viewModel: MainActivityVM): ViewModel
}