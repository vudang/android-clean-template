package com.offeright.android.presenter.user_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.offeright.android.domain.model.UiState
import com.offeright.android.domain.model.UserDetail
import com.offeright.android.domain.stream.authentication.MutableAuthenticatedStream
import com.offeright.android.domain.usecase.user.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val authenticatedStream: MutableAuthenticatedStream,
    private val getUserDetailUseCase: GetUserDetailUseCase,
) : ViewModel() {
    private val _logoutState = MutableStateFlow<UiState<Boolean>>(UiState.None)
    val logoutState = _logoutState

    private val _updateState = MutableStateFlow<UiState<Boolean>>(UiState.None)
    val updateState = _updateState

    private val _user = MutableStateFlow<UserDetail?>(null)
    val user = _user

    private val _isDataChanged = MutableStateFlow(false)
    val isDataChanged = _isDataChanged


    init {
        loadUser()
        validateData()
    }

    fun validateFirstName(firstName: String) {
    }

    fun validateLastName(lastName: String) {
    }

    fun validateAddress(address: String) {
    }

    fun updateUserData() {
        _updateState.value = UiState.Loading
        viewModelScope.launch {
        }
    }

    fun logout() {
        _logoutState.value = UiState.Loading
        viewModelScope.launch {
        }
    }

    private fun validateData() {
        viewModelScope.launch {
        }
    }

    private fun loadUser() {
        authenticatedStream.userDetail?.let {
            _user.value = it
        }

        viewModelScope.launch {
            getUserDetailUseCase.execute(Unit).collect {
                if (it is UiState.Success) {
                    _user.value = it.data
                }
            }
        }
    }

    fun resetState() {
        _logoutState.value = UiState.None
        _updateState.value = UiState.None
    }
}