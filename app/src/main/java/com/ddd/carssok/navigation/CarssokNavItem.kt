package com.ddd.carssok.navigation

import androidx.annotation.DrawableRes
import com.ddd.carssok.feature.home.navi.HomeDestination
import com.ddd.carssok.feature.introduce.navi.IntroduceDestination
import com.ddd.carssok.feature.record.navi.RecordDestination

data class CarssokNavItem(
    val title: String,
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int,
    val route: String
)

object CarssokNavItems {
    val fabItem = CarssokNavItem(
        title = "기록하기",
        icon = com.google.android.material.R.drawable.ic_m3_chip_checked_circle,
        selectedIcon = com.google.android.material.R.drawable.ic_m3_chip_checked_circle,
        route = RecordDestination.route
    )

    val topLevelBottomNavItems = listOf<CarssokNavItem>(
        CarssokNavItem(
            title = "내차정보",
            icon = com.google.android.material.R.drawable.ic_m3_chip_checked_circle,
            selectedIcon = com.google.android.material.R.drawable.ic_m3_chip_checked_circle,
            route = HomeDestination.route
        ),
        CarssokNavItem(
            title = "카쏙소개",
            icon = com.google.android.material.R.drawable.ic_m3_chip_checked_circle,
            selectedIcon = com.google.android.material.R.drawable.ic_m3_chip_checked_circle,
            route = IntroduceDestination.route
        )
    )

    fun isTopLevelNavItem(route: String?): Boolean {
        return topLevelBottomNavItems.map { it.route }.contains(route) or (fabItem.route == route)
    }
}