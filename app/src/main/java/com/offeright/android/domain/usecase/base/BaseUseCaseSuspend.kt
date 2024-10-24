package com.offeright.android.domain.usecase.base

abstract class BaseUseCaseSuspend<in Params, out T> {
    abstract suspend fun execute(params: Params): T
}