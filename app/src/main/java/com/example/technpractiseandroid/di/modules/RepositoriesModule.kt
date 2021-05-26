package com.example.technpractiseandroid.di.modules

import com.example.technpractiseandroid.repository.impl.TaskRepositoryImpl
import com.example.technpractiseandroid.repository.impl.UserRepositoryImpl
import com.example.technpractiseandroid.repository.interfaces.TaskRepository
import com.example.technpractiseandroid.repository.interfaces.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideTaskRepo(
        db: FirebaseFirestore
    ): TaskRepository = TaskRepositoryImpl(db)

    @Provides
    @Singleton
    fun provideUserRepo(
        mAuth: FirebaseAuth
    ): UserRepository = UserRepositoryImpl(mAuth)
}