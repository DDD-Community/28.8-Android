package com.ddd.carssok.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddd.carssok.core.designsystem.component.TypeTextPreview
import com.ddd.carssok.core.designsystem.component.input.InputTextPreview
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToOnBoarding: () -> Unit,
) {
    HomeScreen(
        event = viewModel.event,
        navigateToOnBoarding = navigateToOnBoarding,
    )
}

@Composable
fun HomeScreen(
    event: SharedFlow<HomeViewModel.Event>,
    navigateToOnBoarding: () -> Unit,
) {
    LaunchedEffect(Unit) {
        event.collectLatest {
            when (it) {
                is HomeViewModel.Event.CheckedCarssokUser -> {
                    if (it.isCarssokUser.not()) {
                        navigateToOnBoarding()
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
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