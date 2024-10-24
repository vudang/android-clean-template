package com.offeright.android.data.model.response

import com.offeright.android.data.model.base.APIError
import com.offeright.android.domain.model.UiState

sealed class DataResult<out T> {
    data object Loading : DataResult<Nothing>()
    data class Success<out T>(val data: T) : DataResult<T>()
    data class Error(val error: APIError) : DataResult<Nothing>()
    
    fun toUiState(): UiState<T> {
        return when (this) {
            is Success -> UiState.Success(data)
            is Error -> UiState.Error(error.message, error.code)
            is Loading -> UiState.Loading
        }
    }
}
