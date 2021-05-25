package com.example.technpractiseandroid.di.modules

import androidx.lifecycle.ViewModel
import com.example.technpractiseandroid.auth.login.LoginVM
import com.example.technpractiseandroid.di.ViewModelKey
import com.example.technpractiseandroid.tasks.AllTasksVM
import com.example.technpractiseandroid.tasks.CreateTaskVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AllTasksModule {

    @Binds
    @IntoMap
    @ViewModelKey(AllTasksVM::class)
    fun bindAllTasksViewModel(viewModel: AllTasksVM): ViewModel
}