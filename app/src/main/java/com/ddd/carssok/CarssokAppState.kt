package com.ddd.carssok

import androidx.compose.runtime.*
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ddd.carssok.navigation.CarssokNavItems

class CarssokAppState(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val shouldShowAppBarNavigationIcon: Boolean
        @Composable get() = CarssokNavItems.isTopLevelNavItem(currentDestination?.route).not()

    val shouldShowBottomNavigationBar: Boolean
        @Composable get() = CarssokNavItems.isTopLevelNavItem(currentDestination?.route)

    var shouldShowErrorDialog by mutableStateOf(false)
        private set



    @Composable
    fun isCurrentRoute(route: String): Boolean {
        return currentDestination?.route == route
    }

    fun onBackPressed() {
        navController.popBackStack()
    }

    fun navigateTo(route: String) {
        navController.navigate(route)
    }

    fun navigateToBottomNavigation(route: String) {
        navController.navigate(route) {
            navController.graph.startDestinationRoute?.let { // 시작 탭만 스택에 썋이도록
                popUpTo(it) {
                    saveState = true
                }
            }
            launchSingleTop = true  // 화면 인스턴스는 하나만 만들어지도록
            restoreState = true     // 버튼을 재클릭 했을 때 이전 상태가 남아있도록
        }
    }

    fun setShowErrorDialog(shouldShow: Boolean) {
        shouldShowErrorDialog = shouldShow
    }
}

@Composable
fun rememberCarssokAppState(
    navController: NavHostController = rememberNavController()
) = remember {
    CarssokAppState(
        navController = navController
    )
}