package com.offeright.android.domain.model

data class Pagination(
    val totalPages: Int?,
    val currentPage: Int?,
    val itemsPerPage: Int?
): BaseModel