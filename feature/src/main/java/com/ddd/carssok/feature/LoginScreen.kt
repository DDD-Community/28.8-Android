package com.ddd.carssok.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.Appbar
import com.ddd.carssok.core.designsystem.component.BottomNavigation
import com.ddd.carssok.core.designsystem.component.BottomNavigationItem
import com.ddd.carssok.core.designsystem.component.TypoText

@Composable
fun LoginRoute() {
    LoginScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    Scaffold(
        topBar = {
            Appbar(androidx.appcompat.R.string.abc_capital_off)
        },
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = com.google.android.material.R.drawable
                                    .ic_m3_chip_checked_circle
                            ),
                            contentDescription = null
                        )
                    },
                    label = {
                        TypoText(text = "첫번쨰", typoStyle = TypoStyle.BODY_X11_SMALL)
                    }
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = com.google.android.material.R.drawable
                                    .ic_m3_chip_checked_circle
                            ),
                            contentDescription = null
                        )
                    },
                    label = {
                        TypoText(text = "두번째", typoStyle = TypoStyle.BODY_X11_SMALL)
                    }
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            TypoText(text = "headline small 16", typoStyle = TypoStyle.HEADLINE_SMALL_16)
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginRoute()
}