package com.offeright.android.data.datasource.remote

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Url

interface S3UploadService {
    @PUT
    suspend fun uploadFile(
        @Url url: String,
        @Body file: RequestBody
    )
}

data class UploadResponse(
    val success: Boolean?,
    val message: String?
)