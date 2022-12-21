package com.ddd.carssok.feature.onboarding.model

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
fun OnboardingModelRoute(
    onDone: () -> Unit,
    onBackPressed: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    OnboardingModelScreen(
        onButtonClicked = {
            onDone()
        }
    )
}

@Composable
fun OnboardingModelScreen(
    onButtonClicked: () -> Unit,
) {
    Column {
        TypoText(text = "온보드-모델", typoStyle = TypoStyle.BODY_LARGE_16)

        CarssokButton(
            titleRes = R.string.button_title_next,
            isEnabled = true,
            onClicked = onButtonClicked
        )
    }

}

@Preview
@Composable
fun OnboardingModelScreenPreview() {
    OnboardingModelRoute(
        onDone = {},
        onBackPressed = {}
    )
}