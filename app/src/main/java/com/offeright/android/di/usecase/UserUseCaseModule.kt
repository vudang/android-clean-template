package com.offeright.android.di.usecase

import com.offeright.android.domain.repository.auth.GetUserRepository
import com.offeright.android.domain.stream.authentication.MutableAuthenticatedStream
import com.offeright.android.domain.usecase.user.GetUserDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UserUseCaseModule {
    @Provides
    fun provideGetUserUseCase(repository: GetUserRepository, authenticatedStream: MutableAuthenticatedStream): GetUserDetailUseCase {
        return GetUserDetailUseCase(repository, authenticatedStream)
    }
}