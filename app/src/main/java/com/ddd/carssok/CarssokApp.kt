package com.ddd.carssok

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.ddd.carssok.core.designsystem.Theme
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.Appbar
import com.ddd.carssok.core.designsystem.component.BottomNavigation
import com.ddd.carssok.core.designsystem.component.BottomNavigationItem
import com.ddd.carssok.core.designsystem.component.TypoText
import com.ddd.carssok.feature.record.navi.RecordDestination
import com.ddd.carssok.navigation.CarssokNavHost
import com.ddd.carssok.navigation.CarssokNavItems

@Composable
fun CarssokApp(
    appState: CarssokAppState = rememberCarssokAppState()
) {
    Theme {
        // 앱 화면
        CarssokAppScaffold(appState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarssokAppScaffold(
    appState: CarssokAppState
) {
    Scaffold(
    topBar = {
            Appbar(
                titleRes = R.string.app_name,
                onClickedBack = {
                    appState.onBackPressed()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {
                    appState.navigateToBottomNavigation(RecordDestination.route)
                }
            ) {
                Icon(Icons.Filled.Add,"")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
//        isFloatingActionButtonDocked = true,  // FIXME material3 지원 안하는 듯

        bottomBar = {
            if(appState.shouldShowBottomNavigationBar) {
                BottomNavigation() {
                    CarssokNavItems.topLevelBottomNavItems.forEach{ item ->
                        BottomNavigationItem(
                            selected = appState.isCurrentRoute(route = item.route),
                            onClick = {
                                appState.navigateToBottomNavigation(item.route)
                            },
                            icon = {
                                Icon(painter = painterResource(id = item.icon), contentDescription = null)
                            },
                            selectedIcon = {
                                Icon(painter = painterResource(id = item.selectedIcon), contentDescription = null)
                            },
                            label = {
                                TypoText(text = item.title, typoStyle = TypoStyle.BODY_X11_SMALL)
                            }
                        )
                    }
                }
            }
        }
    ) {
        CarssokNavHost(
            modifier = Modifier.padding(it),
            navController = appState.navController
        )
    }
}