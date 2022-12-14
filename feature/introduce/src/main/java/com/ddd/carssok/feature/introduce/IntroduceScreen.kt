package com.ddd.carssok.feature.introduce

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.TypoText

@Composable
fun IntroduceRoute() {
    IntroduceScreen()
}

@Composable
fun IntroduceScreen() {
    TypoText(text = "์นด์์๊ฐ", typoStyle = TypoStyle.BODY_LARGE_16)
}

@Preview
@Composable
fun IntroduceScreenPreview() {
    IntroduceScreen()
}