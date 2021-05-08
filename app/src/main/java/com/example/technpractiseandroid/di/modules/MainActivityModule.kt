package com.example.technpractiseandroid.di.modules

import com.example.technpractiseandroid.base.MainActivityVM
import dagger.Module
import dagger.Provides




@Module
class MainActivityModule {
    @Provides
    fun provideMainActivityViewModel(): MainActivityVM? {
        return MainActivityVM()
    }
}