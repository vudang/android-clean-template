package com.offeright.android.domain.repository.auth

import com.offeright.android.data.model.response.DataResult
import com.offeright.android.domain.model.User
import com.offeright.android.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface GetUserRepository {
    fun getLocalUser(): Flow<DataResult<User>>
    fun fetchRemoteUser(): Flow<DataResult<UserDetail>>
}