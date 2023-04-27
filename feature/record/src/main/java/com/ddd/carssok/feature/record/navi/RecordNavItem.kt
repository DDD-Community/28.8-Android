package com.ddd.carssok.feature.record.navi

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ddd.carssok.feature.record.R

data class RecordNavItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    @StringRes val state: Int,
    val route: String,
)

object RecordNavItems {
    val BottomSheetItems = listOf<RecordNavItem>(
        RecordNavItem(
            title = R.string.record_register_refuel_title,
            icon = R.drawable.ic_refuel,
            state = R.string.record_unregistered_status,
            route = RecordRefuelDestination.route,
        ),
        RecordNavItem(
            title = R.string.record_register_maintenance_title,
            icon = R.drawable.ic_maintenance,
            state = R.string.record_unregistered_status,
            route = RecordMaintenanceDestination.route,
        ),
        RecordNavItem(
            title = R.string.record_register_drive_title,
            icon = R.drawable.ic_drive,
            state = R.string.record_unregistered_status,
            route = RecordDriveDestination.route,
        ),
        RecordNavItem(
            title = R.string.record_register_accident_title,
            icon = R.drawable.ic_accident,
            state = R.string.record_unregistered_status,
            route = RecordAccidentDestination.route,
        ),
    )
}