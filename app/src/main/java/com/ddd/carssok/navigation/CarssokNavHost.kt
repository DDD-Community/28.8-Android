package com.ddd.carssok.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ddd.carssok.feature.home.navi.HomeDestination
import com.ddd.carssok.feature.home.navi.toHomeGraph
import com.ddd.carssok.feature.introduce.navi.toIntroduceGraph
import com.ddd.carssok.feature.record.navi.toRecordGraph

@Composable
fun CarssokNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HomeDestination.route
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        // toplevel route screen
        toHomeGraph(
            navController = navController
        )
        toRecordGraph(
            navController = navController,
        )
        toIntroduceGraph()
    }
}