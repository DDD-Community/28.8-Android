package com.ddd.carssok.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ddd.carssok.R
import com.ddd.carssok.feature.home.navi.HomeDestination
import com.ddd.carssok.feature.introduce.navi.IntroduceDestination
import com.ddd.carssok.feature.record.navi.RecordDestination

data class CarssokNavItem(
    @StringRes val titleResId: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int,
    val route: String
)

object CarssokNavItems {
    val fabItem = CarssokNavItem(
        titleResId = R.string.item_fab_title_record,
        icon = com.google.android.material.R.drawable.ic_m3_chip_checked_circle,
        selectedIcon = com.google.android.material.R.drawable.ic_m3_chip_checked_circle,
        route = RecordDestination.route
    )

    val topLevelBottomNavItems = listOf<CarssokNavItem>(
        CarssokNavItem(
            titleResId = R.string.item_bottom_navigation_title_home,
            icon = R.drawable.ic_bottom_navigation_unselected_home,
            selectedIcon = R.drawable.ic_bottom_navigation_selected_home,
            route = HomeDestination.route
        ),
        CarssokNavItem(
            titleResId = R.string.item_bottom_navigation_title_introduce,
            icon = R.drawable.ic_bottom_navigation_unselected_introduce,
            selectedIcon = R.drawable.ic_bottom_navigation_selected_introduce,
            route = IntroduceDestination.route
        )
    )

    fun isTopLevelNavItem(route: String?): Boolean {
        return topLevelBottomNavItems.map { it.route }.contains(route) or (fabItem.route == route)
    }
}