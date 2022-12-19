package com.ddd.carssok.feature.login

import androidx.compose.runtime.Composable
import com.ddd.carssok.core.designsystem.component.*
import com.ddd.carssok.feature.R

@Composable
fun LoginRoute() {
    LoginScreen()
}

@Composable
fun LoginScreen() {
    CarssokButton(
        titleRes = R.string.button_title_next,
        onClicked = {
            // TODO navigationTo HomeScreen
        }
    )
}

@Composable
fun LoginScreenPreview() {
    LoginRoute()
}