package com.ddd.carssok.feature.onboarding.fuel

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.CarssokButton
import com.ddd.carssok.core.designsystem.component.TypoText
import com.ddd.carssok.feature.onboarding.OnboardingViewModel
import com.ddd.carssok.feature.onboarding.R

@Composable
fun OnboardingFuelRoute(
    onDone: () -> Unit,
    onBackPressed: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    OnboardingFuelScreen(
        onButtonClicked = {
            viewModel.onOnboardingDone()
            onDone()
        }
    )
}

@Composable
fun OnboardingFuelScreen(
    onButtonClicked: () -> Unit,
) {
    Column {
        TypoText(text = "온보드-연료", typoStyle = TypoStyle.BODY_LARGE_16)

        CarssokButton(
            titleRes = R.string.button_title_next,
            isEnabled = true,
            onClicked = onButtonClicked
        )
    }

}

@Preview
@Composable
fun OnboardingFuelScreenPreview() {
    OnboardingFuelRoute(
        onDone = {},
        onBackPressed = {}
    )
}