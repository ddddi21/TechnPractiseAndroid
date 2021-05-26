package com.example.technpractiseandroid.di.modules

import com.example.technpractiseandroid.api.BoredApi
import com.example.technpractiseandroid.repository.impl.TaskRepositoryImpl
import com.example.technpractiseandroid.repository.impl.UserRepositoryImpl
import com.example.technpractiseandroid.repository.interfaces.TaskRepository
import com.example.technpractiseandroid.repository.interfaces.UserRepository
import com.example.technpractiseandroid.repository.WordRepository
import com.example.technpractiseandroid.repository.impl.ApiRepositoryImpl
import com.example.technpractiseandroid.repository.interfaces.ApiRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideTaskRepo(
        db: FirebaseFirestore,
        @Named("IO")coroutineContext: CoroutineContext
    ): TaskRepository = TaskRepositoryImpl(db, coroutineContext)

    @Provides
    @Singleton
    fun provideUserRepo(
        mAuth: FirebaseAuth
    ): UserRepository = UserRepositoryImpl(mAuth)

    @Provides
    @Singleton
    fun provideWordRepo(): WordRepository = WordRepository

    @Provides
    @Singleton
    fun provideApiRepo(
        boredApi: BoredApi
    ): ApiRepository = ApiRepositoryImpl(boredApi)
}