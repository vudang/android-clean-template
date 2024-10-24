package com.offeright.android.di.repository

import com.offeright.android.data.datasource.local.db.AppDatabase
import com.offeright.android.data.datasource.remote.NetworkService
import com.offeright.android.data.repository.auth.GetUserRepositoryImpl
import com.offeright.android.domain.repository.auth.GetUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthRepositoryModule {
    @Provides
    @Singleton
    fun provideGetUserRepository(database: AppDatabase, networkService: NetworkService): GetUserRepository {
        return GetUserRepositoryImpl(database, networkService)
    }
}