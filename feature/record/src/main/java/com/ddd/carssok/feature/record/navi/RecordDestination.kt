package com.ddd.carssok.feature.record.navi

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.ddd.carssok.core.navigator.CarssokNavigationDestination
import com.ddd.carssok.feature.record.accident.RecordAccidentRoute
import com.ddd.carssok.feature.record.drive.RecordDriveRoute
import com.ddd.carssok.feature.record.maintenance.RecordMaintenanceRoute
import com.ddd.carssok.feature.record.refuel.RecordRefuelRoute

object RecordDestination : CarssokNavigationDestination {
    override val route: String = "record_route"
    override val destination: String = "record_destination"
}

object RecordRefuelDestination : CarssokNavigationDestination {
    override val route: String = "record_refuel_route"
    override val destination: String = "record_refuel_destination"
}

object RecordMaintenanceDestination : CarssokNavigationDestination {
    override val route: String = "record_maintenance_route"
    override val destination: String = "record_maintenance_destination"
}

object RecordDriveDestination : CarssokNavigationDestination {
    override val route: String = "record_drive_route"
    override val destination: String = "record_drive_destination"
}

object RecordAccidentDestination : CarssokNavigationDestination {
    override val route: String = "record_accident_route"
    override val destination: String = "record_accident_destination"
}

fun NavGraphBuilder.toRecordGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = RecordRefuelDestination.route,
        route = RecordDestination.route,
    ) {
        composable(
            route = RecordRefuelDestination.route
        ) {
            RecordRefuelRoute()
        }

        composable(
            route = RecordMaintenanceDestination.route
        ) {
            RecordMaintenanceRoute()
        }

        composable(
            route = RecordDriveDestination.route
        ) {
            RecordDriveRoute()
        }

        composable(
            route = RecordAccidentDestination.route
        ) {
            RecordAccidentRoute()
        }
    }
}