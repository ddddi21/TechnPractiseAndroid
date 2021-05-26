package com.example.technpractiseandroid.di.modules

import androidx.lifecycle.ViewModel
import com.example.technpractiseandroid.auth.login.LoginVM
import com.example.technpractiseandroid.di.ViewModelKey
import com.example.technpractiseandroid.profile.`fun`.GetFunVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface GetFunModule {

    @Binds
    @IntoMap
    @ViewModelKey(GetFunVM::class)
    fun bindLoginViewModel(viewModel: GetFunVM): ViewModel
}