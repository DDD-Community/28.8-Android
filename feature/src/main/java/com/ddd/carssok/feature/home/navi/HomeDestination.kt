package com.ddd.carssok.feature.home.navi

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ddd.carssok.core.navigator.CarssokNavigationDestination
import com.ddd.carssok.feature.home.HomeRoute
import com.ddd.carssok.feature.home.HomeScreen

object HomeDestination : CarssokNavigationDestination {
    override val route: String = "home_route"
    override val destination: String = "home_destination"
}

fun NavGraphBuilder.toHomeGraph() {
    composable(
        route = HomeDestination.route
    ) {
        HomeRoute()
    }
}