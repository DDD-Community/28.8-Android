package com.ddd.carssok.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddd.carssok.core.designsystem.component.InputTextPreview
import com.ddd.carssok.core.designsystem.component.TypeTextPreview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToOnBoarding: () -> Unit,
) {
    val onboardingState = viewModel.onboardingState

    HomeScreen(
        shouldShowOnBoarding = onboardingState,
        navigateToOnBoarding = navigateToOnBoarding,
    )
}

@Composable
fun HomeScreen(
    shouldShowOnBoarding: Boolean,
    navigateToOnBoarding: () -> Unit,
) {
    if(shouldShowOnBoarding) {
        LaunchedEffect(Unit) {
            navigateToOnBoarding()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TypeTextPreview()
        InputTextPreview()
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeRoute(
        navigateToOnBoarding = {}
    )
}