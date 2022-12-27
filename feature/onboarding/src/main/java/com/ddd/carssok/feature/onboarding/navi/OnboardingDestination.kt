package com.ddd.carssok.feature.onboarding.navi

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ddd.carssok.core.navigator.CarssokNavigationDestination
import com.ddd.carssok.feature.onboarding.brand.OnBoardingBrandRoute
import com.ddd.carssok.feature.onboarding.fuel.OnboardingFuelRoute
import com.ddd.carssok.feature.onboarding.model.OnboardingModelRoute

object OnboardingDestination : CarssokNavigationDestination {
    override val route: String = "onboarding_route"
    override val destination: String = "onboarding_destination"
}

private object OnboardingBrandDestination : CarssokNavigationDestination {
    override val route: String = "onboarding_brand_route"
    override val destination: String = "onboarding_brand_destination"
}

private object OnboardingModelDestination : CarssokNavigationDestination {
    override val route: String = "onboarding_model_route"
    override val destination: String = "onboarding_model_destination"
}

private object OnboardingFuelDestination : CarssokNavigationDestination {
    override val route: String = "onboarding_fuel"
    override val destination: String = "onboarding_fuel_destination"
}

fun NavGraphBuilder.toOnBoardingGraph(
    navController: NavHostController,
    onOnBoardingDone: () -> Unit,
    onBackPressed: () -> Unit,
) {
    navigation(
        startDestination = OnboardingBrandDestination.route,
        route = OnboardingDestination.route
    ) {
        composable(
            route = OnboardingBrandDestination.route,
        ) {
            OnBoardingBrandRoute(
                onDone = {
                    navController.navigate(OnboardingModelDestination.route)
                },
                onBackPressed = onBackPressed
            )
        }

        composable(
            route = OnboardingModelDestination.route,
        ) {
            OnboardingModelRoute(
                onDone = {
                    navController.navigate(OnboardingFuelDestination.route)
                },
                onBackPressed = onBackPressed
            )
        }

        composable(
            route = OnboardingFuelDestination.route,
        ) {
            OnboardingFuelRoute(
                onDone = onOnBoardingDone,
                onBackPressed = onBackPressed
            )
        }
    }
}