package com.ddd.carssok.feature.login.navi

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ddd.carssok.core.navigator.CarssokNavigationDestination
import com.ddd.carssok.feature.login.LoginRoute

object LoginDestination : CarssokNavigationDestination {
    override val route: String = "login_route"
    override val destination: String = "login_destination"
}

fun NavGraphBuilder.toLoginGraph() {
    composable(
        route = LoginDestination.route,
    ) {
        LoginRoute()
    }
}