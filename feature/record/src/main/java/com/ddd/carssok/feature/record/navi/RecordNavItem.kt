package com.ddd.carssok.feature.record.navi

import androidx.annotation.DrawableRes

data class RecordNavItem(
    val title: String,
    @DrawableRes val icon: Int,
    val state: String,
    val route: String,
)

object RecordNavItems {
    val BottomSheetItems = listOf<RecordNavItem>(
        RecordNavItem(
            title = "주유기록 등록",
            icon = com.ddd.carssok.core.designsystem.R.drawable.ic_refuel,
            state = "미동록 상태",
            route = RecordRefuelDestination.route,
        ),
        RecordNavItem(
            title = "정비기록 등록",
            icon = com.ddd.carssok.core.designsystem.R.drawable.ic_refuel,
            state = "미동록 상태",
            route = RecordMaintenanceDestination.route,
        ),
        RecordNavItem(
            title = "주행기록 등록",
            icon = com.ddd.carssok.core.designsystem.R.drawable.ic_refuel,
            state = "미동록 상태",
            route = RecordDriveDestination.route,
        ),
        RecordNavItem(
            title = "사고기록 등록",
            icon = com.ddd.carssok.core.designsystem.R.drawable.ic_refuel,
            state = "미동록 상태",
            route = RecordAccidentDestination.route,
        ),
    )
}