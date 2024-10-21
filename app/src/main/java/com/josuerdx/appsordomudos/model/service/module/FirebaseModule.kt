package com.josuerdx.appsordomudos.model.service.module

import com.google.firebase.auth.FirebaseAuth
import com.josuerdx.appsordomudos.model.service.impl.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthService(auth: FirebaseAuth): AuthService {
        return AuthService(auth)
    }
}
