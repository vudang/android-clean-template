package com.offeright.android.presenter.root

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.offeright.android.presenter.router.AppRoute
import com.offeright.android.presenter.router.AppRouter
import com.offeright.android.presenter.router.appComposable
import com.offeright.android.presenter.home.HomeScreen
import com.offeright.android.presenter.user_detail.UserDetailScreen

/** Created by github.com/im-o on 10/28/2023. */

@Composable
fun MainApp(
    windowSize: WindowWidthSizeClass
) {
    val navController = rememberNavController()
    val appRouter = AppRouter(navController = navController)
    NavHost(navController = navController, startDestination = AppRoute.ROOT.getName()) {
        appComposable(AppRoute.ROOT) {
            MainScreen(
                windowSize = windowSize,
                router = appRouter
            )
        }
        appComposable(AppRoute.USER_DASHBOARD) {
            HomeScreen(
                windowSize = windowSize,
                router = appRouter
            )
        }
        appComposable(AppRoute.USER_DETAIL) {
            UserDetailScreen(
                windowSize = windowSize,
                router = appRouter
            )
        }
    }
}