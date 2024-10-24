package com.offeright.android.data.model.response

import com.google.gson.annotations.SerializedName
import com.offeright.android.data.model.base.BaseEntity
import com.offeright.android.domain.model.User

data class UserEntity(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("first_name")
    val firstName: String? = null,
    @SerializedName("last_name")
    val lastName: String? = null,
    @SerializedName("access_token")
    val accessToken: String? = null,
): BaseEntity {
    override fun toModel(): User {
        return User(
            id = id,
            email = email,
            firstName = firstName,
            lastName = lastName,
            accessToken = accessToken
        )
    }
}
