package com.ddd.carssok

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ddd.carssok.core.designsystem.Theme
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.BottomNavigation
import com.ddd.carssok.core.designsystem.component.BottomNavigationItem
import com.ddd.carssok.core.designsystem.component.TypoText
import com.ddd.carssok.feature.record.RecordNavigationBottomSheetContent
import com.ddd.carssok.navigation.CarssokNavHost
import com.ddd.carssok.navigation.CarssokNavItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CarssokApp(
    appState: CarssokAppState = rememberCarssokAppState()
) {
    Theme {
        val scope = rememberCoroutineScope()

        // TODO Back Press Handler 추가

        CarssokBottonSheetAppScaffold(
            appState = appState,
            scope = scope
        ) {
            // 앱 화면
            CarssokAppScaffold(
                appState = appState,
                scope = scope
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CarssokBottonSheetAppScaffold(
    appState: CarssokAppState,
    scope: CoroutineScope,
    content: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        sheetContent = {
            RecordNavigationBottomSheetContent(
                onItemClicked = {
                    scope.launch {
                        appState.navigateTo(it.route)
                        appState.modalBottomSheetState.hide()
                    }
                }
            )
        },
        sheetState = appState.modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        sheetBackgroundColor = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_bg),
    ) {
        content()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CarssokAppScaffold(
    appState: CarssokAppState,
    scope: CoroutineScope,
) {
    Scaffold(
        floatingActionButton = {
            if(appState.shouldShowBottomNavigationBar) {
                FloatingActionButton(
                    modifier = Modifier.offset(y = 40.dp), // NavigationBarTokens.ContainerHeight / 2
                    shape = CircleShape,
                    backgroundColor = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_text),
                    contentColor = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_bg),
                    onClick = {
                        scope.launch {
                            appState.modalBottomSheetState.show()
                        }
                    }
                ) {
                    Icon(Icons.Filled.Add, "")
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        backgroundColor = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_bg),
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
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    tint = colorResource(id = com.ddd.carssok.core.designsystem.R.color.secondary_text),
                                    contentDescription = null
                                )
                            },
                            selectedIcon = {
                                Icon(
                                    painter = painterResource(id = item.selectedIcon),
                                    tint = colorResource(id = R.color.white),
                                    contentDescription = null
                                )
                            },
                            label = {
                                item.titleResId?.let {
                                    TypoText(
                                        text = stringResource(id = item.titleResId),
                                        typoStyle = TypoStyle.BODY_X11_SMALL
                                    )
                                }
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