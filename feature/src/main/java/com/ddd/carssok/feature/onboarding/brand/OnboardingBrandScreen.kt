package com.ddd.carssok.feature.onboarding.brand

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.CarssokButton
import com.ddd.carssok.core.designsystem.component.TypoText
import com.ddd.carssok.feature.R
import com.ddd.carssok.feature.onboarding.OnboardingViewModel

@Composable
fun OnboardingBrandRoute(
    onDone: () -> Unit,
    onBackPressed: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    OnboardingBrandScreen(
        onButtonClicked = {
            onDone()
        }
    )
}

@Composable
fun OnboardingBrandScreen(
    onButtonClicked: () -> Unit,
) {
    Column {
        TypoText(text = "온보드-브랜드", typoStyle = TypoStyle.BODY_LARGE_16)

        CarssokButton(
            titleRes = R.string.button_title_next,
            isEnabled = true,
            onClicked = onButtonClicked
        )
    }

}

@Preview
@Composable
fun OnboardingBrandScreenPreview() {
    OnboardingBrandRoute(
        onDone = {},
        onBackPressed = {}
    )
}