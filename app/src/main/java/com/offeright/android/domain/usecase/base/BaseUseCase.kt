package com.offeright.android.domain.usecase.base

abstract class BaseUseCase<in Params, out T> {
    abstract fun execute(params: Params): T
}