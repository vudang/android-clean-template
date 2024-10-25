package com.offeright.android.presenter.home

import androidx.activity.compose.BackHandler
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.offeright.android.presenter.router.AppRoute
import com.offeright.android.presenter.router.AppRouter
import com.offeright.android.presenter.theme.AppColor

@Composable
fun HomeScreen(
    windowSize: WindowWidthSizeClass,
    router: AppRouter,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val useDarkIcons = MaterialTheme.colors.isLight
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = AppColor.Clear,
        darkIcons = useDarkIcons
    )

    val offers by remember { viewModel.userList }.collectAsState()

    BackHandler {
        // Do nothing to disable back
    }

    fun navigateToProfile() {
        router.push(to = AppRoute.USER_DETAIL)
    }

    /// This is a custom composable that observes the view lifecycle
    /// To refresh the offer list when the view appears
    ObserverViewLifecycle(onViewWillAppear = {
        viewModel.fetchUserList()
    })

    when(windowSize) {
        WindowWidthSizeClass.Compact -> HomeScreenPhone(
            selectedOffer = { offer ->
                viewModel.selectUser(offer)
                navigateToProfile()
            }
        )
        else -> { }
    }
}

@Composable
private fun ObserverViewLifecycle(onViewWillAppear: () -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val currentOnViewWillAppear by rememberUpdatedState(onViewWillAppear)

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                currentOnViewWillAppear()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}