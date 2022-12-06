package com.ddd.carssok.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ddd.carssok.core.designsystem.component.Appbar
import com.ddd.carssok.core.designsystem.component.BottomNavigationPreview
import com.ddd.carssok.core.designsystem.component.InputTextPreview
import com.ddd.carssok.core.designsystem.component.TypeTextPreview

@Composable
fun LoginRoute() {
    LoginScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            Appbar(androidx.appcompat.R.string.abc_capital_off)
        },
        bottomBar = {
            BottomNavigationPreview()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TypeTextPreview()
            InputTextPreview()
        }
    }
}

@Composable
fun LoginScreenPreview() {
    LoginRoute()
}