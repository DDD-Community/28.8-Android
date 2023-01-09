package com.ddd.carssok.feature.onboarding.navi

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ddd.carssok.core.navigator.CarssokNavigationDestination
import com.ddd.carssok.feature.onboarding.brand.OnBoardingBrandRoute
import com.ddd.carssok.feature.onboarding.fuel.OnBoardingFuelRoute
import com.ddd.carssok.feature.onboarding.model.OnBoardingModelRoute

object OnBoardingDestination : CarssokNavigationDestination {
    override val route: String = "onboarding_route"
    override val destination: String = "onboarding_destination"
}

private object OnBoardingBrandDestination : CarssokNavigationDestination {
    override val route: String = "onboarding_brand_route"
    override val destination: String = "onboarding_brand_destination"
}

private object OnBoardingModelDestination : CarssokNavigationDestination {
    override val route: String = "onboarding_model_route"
    override val destination: String = "onboarding_model_destination"
}

private object OnBoardingFuelDestination : CarssokNavigationDestination {
    override val route: String = "onboarding_fuel"
    override val destination: String = "onboarding_fuel_destination"
}

fun NavGraphBuilder.toOnBoardingGraph(
    navController: NavHostController,
    onOnBoardingDone: () -> Unit,
    onBackPressed: () -> Unit,
) {
    navigation(
        startDestination = OnBoardingBrandDestination.route,
        route = OnBoardingDestination.route
    ) {
        composable(
            route = OnBoardingBrandDestination.route,
        ) {
            OnBoardingBrandRoute(
                onDone = {
                    navController.navigate(OnBoardingModelDestination.route)
                },
                onBackPressed = onBackPressed
            )
        }

        composable(
            route = OnBoardingModelDestination.route,
        ) {
            OnBoardingModelRoute(
                onDone = {
                    navController.navigate(OnBoardingFuelDestination.route)
                },
                onBackPressed = onBackPressed
            )
        }

        composable(
            route = OnBoardingFuelDestination.route,
        ) {
            OnBoardingFuelRoute(
                onClickedDone = onOnBoardingDone,
                onBackPressed = onBackPressed
            )
        }
    }
}