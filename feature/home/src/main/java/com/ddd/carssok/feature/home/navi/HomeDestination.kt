package com.ddd.carssok.feature.home.navi

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ddd.carssok.core.navigator.CarssokNavigationDestination
import com.ddd.carssok.feature.home.HomeRoute
import com.ddd.carssok.feature.onboarding.navi.OnBoardingDestination
import com.ddd.carssok.feature.onboarding.navi.toOnBoardingGraph

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
            navigateToOnBoarding = {
                navController.navigate(OnBoardingDestination.route)
            }
        )
    }

    // sub graph - onboarding
    toOnBoardingGraph(
        navController = navController,
        onOnBoardingDone = {
            navController.navigate(HomeDestination.route) {
                popUpTo(HomeDestination.route) {
                    inclusive = true
                    saveState = false
                }
            }
        },
        onBackPressed = {
            navController.popBackStack()
        }
    )
}