package com.offeright.android.presenter.module_template

import androidx.activity.compose.BackHandler
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.offeright.android.presenter.router.AppRouter

@Composable
fun TemplateScreen(
    windowSize: WindowWidthSizeClass,
    router: AppRouter,
    viewModel: TemplateViewModel = hiltViewModel()
) {
    BackHandler {
        // Do nothing to disable back
    }

    when(windowSize) {
        WindowWidthSizeClass.Compact -> TemplateScreenPhone()
        else -> TemplateScreenTablet()
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL)
@Composable
fun TemplateScreenPreview() {
    TemplateScreen(windowSize = WindowWidthSizeClass.Compact, router = AppRouter(navController = rememberNavController()))
}