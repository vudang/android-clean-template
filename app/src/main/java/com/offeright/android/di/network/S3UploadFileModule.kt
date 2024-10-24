package com.offeright.android.di.network

import com.offeright.android.BuildConfig
import com.offeright.android.data.datasource.remote.S3UploadService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/** Created by Tony on 12/16/2024. */

@Module
@InstallIn(SingletonComponent::class)
object S3UploadFileModule{
    @Provides
    @Singleton
    fun provideS3Service(logging: HttpLoggingInterceptor): S3UploadService {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(180, TimeUnit.SECONDS)
            .readTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(180, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(S3UploadService::class.java)
    }
}