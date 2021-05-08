package com.example.technpractiseandroid.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.technpractiseandroid.di.VMFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: VMFactory): ViewModelProvider.Factory
}