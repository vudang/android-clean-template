package com.offeright.android.data.model.base

import com.offeright.android.domain.model.BaseModel

interface BaseEntity {
    fun toModel(): BaseModel
}