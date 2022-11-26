package com.ddd.carssok

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.ddd.carssok.core.designsystem.Theme
import com.ddd.carssok.navigation.CarssokNavHost

@Composable
fun CarssokApp() {
    Theme {
        val navController = rememberNavController()
        CarssokNavHost(
            navController = navController
        )
    }
}