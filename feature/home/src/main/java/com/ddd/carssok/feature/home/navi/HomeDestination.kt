package com.ddd.carssok.feature.home.navi

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ddd.carssok.core.navigator.CarssokNavigationDestination
import com.ddd.carssok.feature.home.HomeRoute
import com.ddd.carssok.feature.onboarding.navi.OnBoardingDestination
import com.ddd.carssok.feature.onboarding.navi.toOnBoardingGraph
import com.ddd.carssok.feature.record.navi.RecordDriveHistoryDestination
import com.ddd.carssok.feature.record.navi.RecordRefuelListDestination
import com.ddd.carssok.feature.record.navi.toRecordGraph

object HomeDestination : CarssokNavigationDestination {
    override val route: String = "home_route"
    override val destination: String = "home_destination"
}

fun NavGraphBuilder.toHomeGraph(
    navController: NavHostController
) {
    composable(
        route = HomeDestination.route
    ) {
        HomeRoute(
            navigateToOnBoarding = {
                navController.navigate(OnBoardingDestination.route)
            },
            // TODO 임시 진입점, 추후 제거
            navigateToRecordHistory = {
                when (it) {
                    "주행 목록" -> {
                        navController.navigate(RecordDriveHistoryDestination.route)
                    }
                    "주유 목록" -> {
                        navController.navigate(RecordRefuelListDestination.route)
                    }
                    else -> Unit
                }
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

    toRecordGraph(
        navController = navController,
    )
}