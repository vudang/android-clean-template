package com.offeright.android.presenter.user_detail

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.offeright.android.domain.model.UiState
import com.offeright.android.presenter.component.AppLoadingIndicator
import com.offeright.android.presenter.router.AppRoute
import com.offeright.android.presenter.router.AppRouter

@Composable
fun UserDetailScreen(
    windowSize: WindowWidthSizeClass,
    router: AppRouter,
    viewModel: UserDetailViewModel = hiltViewModel()
) {
    val logoutState by remember { viewModel.logoutState }.collectAsState()
    val updateState by remember { viewModel.updateState }.collectAsState()

    fun navigateToUserDashboard() {
        viewModel.resetState()
        router.pop()
    }

    fun navigateToLogin() {
        router.popTo(AppRoute.ROOT)
    }

    fun navigateToChangePassword() {
    }

    when(windowSize) {
        WindowWidthSizeClass.Compact -> UserDetailScreenPhone(
            closeClick = { navigateToUserDashboard() },
            logoutClick = { viewModel.logout() },
            changePassClick = { navigateToChangePassword() }
        )
        else -> UserDetailScreenTablet(
            closeClick = { navigateToUserDashboard() },
            logoutClick = { viewModel.logout() },
            changePassClick = { navigateToChangePassword() }
        )
    }

    when (logoutState) {
        is UiState.Success -> navigateToLogin()
        is UiState.Loading -> { AppLoadingIndicator() }
        else -> {}
    }

    when (updateState) {
        is UiState.Success -> navigateToUserDashboard()
        is UiState.Loading -> { AppLoadingIndicator() }
        else -> {}
    }
}