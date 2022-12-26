package com.ddd.carssok.feature.record.navi

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ddd.carssok.core.navigator.CarssokNavigationDestination
import com.ddd.carssok.feature.record.RecordRoute

object RecordDestination : CarssokNavigationDestination {
    override val route: String = "record_route"
    override val destination: String = "record_destination"
}

fun NavGraphBuilder.toRecordGraph() {
    composable(
        route = RecordDestination.route
    ) {
        RecordRoute()
    }
}