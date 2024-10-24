package com.offeright.android.domain.usecase.user

import com.offeright.android.data.model.response.DataResult
import com.offeright.android.domain.model.UiState
import com.offeright.android.domain.model.UserDetail
import com.offeright.android.domain.repository.auth.GetUserRepository
import com.offeright.android.domain.stream.authentication.MutableAuthenticatedStream
import com.offeright.android.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val getUserRepository: GetUserRepository,
    private val authenticatedUseCase: MutableAuthenticatedStream,
) : BaseUseCase<Unit, Flow<UiState<UserDetail>>>() {
    override fun execute(params: Unit): Flow<UiState<UserDetail>> {
        return getUserRepository.fetchRemoteUser()
            .map { res ->
                if (res is DataResult.Success<UserDetail>) {
                    authenticatedUseCase.update(res.data)
                }
                res.toUiState()
            }
    }
}