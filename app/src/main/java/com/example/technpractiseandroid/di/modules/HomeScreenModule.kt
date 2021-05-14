package com.example.technpractiseandroid.di.modules

import androidx.lifecycle.ViewModel
import com.example.technpractiseandroid.auth.login.LoginVM
import com.example.technpractiseandroid.di.ViewModelKey
import com.example.technpractiseandroid.main.HomeScreenVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HomeScreenModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeScreenVM::class)
    fun bindLoginViewModel(viewModel: HomeScreenVM): ViewModel
}