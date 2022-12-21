package com.ddd.carssok.feature.home.navi

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ddd.carssok.core.navigator.CarssokNavigationDestination
import com.ddd.carssok.feature.home.HomeRoute
import com.ddd.carssok.feature.onboarding.navi.OnboardingDestination
import com.ddd.carssok.feature.onboarding.navi.toOnboardingGraph

object HomeDestination : CarssokNavigationDestination {
    override val route: String = "home_route"
    override val destination: String = "home_destination"
}

fun NavGraphBuilder.toHomeGraph(
    navController: NavHostController,
) {
    composable(
        route = HomeDestination.route
    ) {
        HomeRoute(
            navigateToOnboarding = {
                navController.navigate(OnboardingDestination.route)
            }
        )
    }

    // sub graph - onboarding
    toOnboardingGraph(
        navController = navController,
        onOnboardingDone = {
            navController.navigate(HomeDestination.route) {
                popUpTo(HomeDestination.route) {
                    inclusive = true
                    saveState = true
                }
            }
        },
        onBackPressed = {
            // TODO 온보딩 완료 안하고 뒤로갈 경우, 앱 종료하기
        }
    )
}