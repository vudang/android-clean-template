package com.offeright.android.data.datasource.remote

import com.offeright.android.data.model.base.Response
import com.offeright.android.data.model.response.UserDetailEntity
import retrofit2.http.GET

/** Created by Tony on 10/1/2024. */

interface NetworkService {
    @GET("api/v1/users/profile")
    suspend fun getUserDetail(): Response<UserDetailEntity>
}