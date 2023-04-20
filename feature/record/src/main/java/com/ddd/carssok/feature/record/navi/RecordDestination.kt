package com.ddd.carssok.feature.record.navi

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.ddd.carssok.core.navigator.CarssokNavigationDestination
import com.ddd.carssok.feature.record.accident.RecordAccidentRoute
import com.ddd.carssok.feature.record.drive.RecordDriveHistoryRoute
import com.ddd.carssok.feature.record.drive.RecordDriveRoute
import com.ddd.carssok.feature.record.maintenance.RecordMaintenanceRoute
import com.ddd.carssok.feature.record.refuel.RecordRefuelListRoute
import com.ddd.carssok.feature.record.refuel.RecordRefuelRoute


object RecordDestination : CarssokNavigationDestination {
    override val route: String = "record_route"
    override val destination: String = "record_destination"
}

// 주유 기록
object RecordRefuelDestination : CarssokNavigationDestination {
    const val ARGS_ID = "id"
    private const val baseRoute: String = "record_refuel_route"

    override val route: String = "$baseRoute?$ARGS_ID={$ARGS_ID}"
    override val destination: String = "record_refuel_destination"

    fun routeWithId(id: Int): String {
        return "$baseRoute?$ARGS_ID=$id"
    }
}

object RecordRefuelListDestination : CarssokNavigationDestination {
    override val route: String = "record_refuel_list_route"
    override val destination: String = "record_refuel_list_destination"
}

object RecordMaintenanceDestination : CarssokNavigationDestination {
    override val route: String = "record_maintenance_route"
    override val destination: String = "record_maintenance_destination"
}

// 주행 기록
object RecordDriveDestination : CarssokNavigationDestination {
    override val route: String = "record_drive_route"
    override val destination: String = "record_drive_destination"
}

// 이전 주행 기록
object RecordDriveHistoryDestination : CarssokNavigationDestination {
    override val route: String = "record_drive_history_route"
    override val destination: String = "record_drive_history_destination"
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
        // 주유 기록하기
        composable(
            route = RecordRefuelDestination.route,
            arguments = listOf(
                navArgument(RecordRefuelDestination.ARGS_ID) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            RecordRefuelRoute(
                id = backStackEntry.arguments?.getInt(RecordRefuelDestination.ARGS_ID) ?: -1,
                onClickedBack = {
                    navController.popBackStack()
                }
            )
        }

        // 주유 기록 목록
        composable(
            route = RecordRefuelListDestination.route
        ) {
            RecordRefuelListRoute(
                onClickedBack = {
                    navController.popBackStack()
                },
                navigateToRecordRefuel = {
                    navController.navigate(RecordRefuelDestination.route)
                },
                navigateToRefuelDetail = {
                    navController.navigate(RecordRefuelDestination.routeWithId(it))
                }
            )
        }

        // 점검 기록하기
        composable(
            route = RecordMaintenanceDestination.route
        ) {
            RecordMaintenanceRoute()
        }

        // TODO 점검 기록 목록


        // 주행 기록하기
        composable(
            route = RecordDriveDestination.route
        ) {
            RecordDriveRoute(
                navigateToPreviousHistory = {
                    navController.navigate(route = RecordDriveHistoryDestination.route)
                },
                onClickedBack = {
                    navController.popBackStack()
                }
            )
        }

        // 이전 주행 기록 목록
        composable(
            route = RecordDriveHistoryDestination.route
        ) {
            RecordDriveHistoryRoute(
                onClickedBack = {
                    navController.popBackStack()
                }
            )
        }

        // 사고 기록하기
        composable(
            route = RecordAccidentDestination.route
        ) {
            RecordAccidentRoute(
                onClickedBack = {
                    navController.popBackStack()
                }
            )
        }

        // TODO 사고 기록 목록
    }
}