package com.example.technpractiseandroid.di.modules

import androidx.lifecycle.ViewModel
import com.example.technpractiseandroid.auth.login.LoginVM
import com.example.technpractiseandroid.di.ViewModelKey
import com.example.technpractiseandroid.profile.ProfileVm
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ProfileModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileVm::class)
    fun bindLoginViewModel(viewModel: ProfileVm): ViewModel
}