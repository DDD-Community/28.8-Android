package com.ddd.carssok.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddd.carssok.core.designsystem.component.CarssokButton
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToOnBoarding: () -> Unit,
    navigateToRecordHistory: (String) -> Unit,
) {
    HomeScreen(
        event = viewModel.event,
        navigateToOnBoarding = navigateToOnBoarding,
        navigateToRecordHistory = navigateToRecordHistory,
    )
}

@Composable
fun HomeScreen(
    event: SharedFlow<HomeViewModel.Event>,
    navigateToOnBoarding: () -> Unit,
    navigateToRecordHistory: (String) -> Unit,
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
        // TODO 임시 진입점, 추후 제거
        CarssokButton(
            modifier = Modifier.fillMaxWidth(),
            titleRes = R.string.navigate_to_drive_history,
            isEnabled = true,
            onClicked = {
                navigateToRecordHistory("주행 목록")
            }
        )
        CarssokButton(
            modifier = Modifier.fillMaxWidth(),
            titleRes = R.string.navigate_to_refuel_history,
            isEnabled = true,
            onClicked = {
                navigateToRecordHistory("주유 목록")
            }
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeRoute(
        navigateToOnBoarding = {},
        navigateToRecordHistory = {}
    )
}