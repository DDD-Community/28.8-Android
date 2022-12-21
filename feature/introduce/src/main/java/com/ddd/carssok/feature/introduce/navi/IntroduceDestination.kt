package com.ddd.carssok.feature.introduce.navi

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ddd.carssok.core.navigator.CarssokNavigationDestination
import com.ddd.carssok.feature.introduce.IntroduceRoute
import com.ddd.carssok.feature.introduce.IntroduceScreen

object IntroduceDestination : CarssokNavigationDestination {
    override val route: String = "introduce_route"
    override val destination: String = "introduce_destination"
}

fun NavGraphBuilder.toIntroduceGraph() {
    composable(
        route = IntroduceDestination.route
    ) {
        IntroduceRoute()
    }
}