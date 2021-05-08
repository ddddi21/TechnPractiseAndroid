package com.example.technpractiseandroid.di.modules

import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Binds
    fun provideFirebaseCurrentUser(firebaseAuth: FirebaseAuth) = firebaseAuth.currentUser

}