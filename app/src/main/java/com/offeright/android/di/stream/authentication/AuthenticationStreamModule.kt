package com.offeright.android.di.stream.authentication

import com.offeright.android.domain.stream.authentication.AuthenticatedStreamImpl
import com.offeright.android.domain.stream.authentication.MutableAuthenticatedStream
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationStreamModule {

    @Provides
    @Singleton
    fun provideMutableAuthenticatedStream(): MutableAuthenticatedStream {
        return AuthenticatedStreamImpl()
    }
}