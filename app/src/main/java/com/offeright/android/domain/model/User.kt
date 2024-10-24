package com.offeright.android.domain.model

import com.offeright.android.data.datasource.local.db.entity.user.UserStore

data class User(
    val id: Int? = null,
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val accessToken: String? = null,
): BaseModel {
    fun getFullName() = "$firstName $lastName"
}

fun User.toStore(): UserStore {
    return UserStore(
        id = id ?: 0,
        email = email,
        firstName = firstName,
        lastName = lastName,
        accessToken = accessToken
    )
}