package com.example.technpractiseandroid.di.modules

import androidx.lifecycle.ViewModel
import com.example.technpractiseandroid.auth.login.LoginVM
import com.example.technpractiseandroid.di.ViewModelKey
import com.example.technpractiseandroid.tasks.CreateTaskVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CreateTaskModule {

    @Binds
    @IntoMap
    @ViewModelKey(CreateTaskVM::class)
    fun bindLoginViewModel(viewModel: CreateTaskVM): ViewModel
}