package com.ddd.carssok.feature.record.accident

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.TypoText

@Composable
fun RecordAccidentRoute() {
    RecordAccidentScreen()
}

@Composable
fun RecordAccidentScreen() {
    TypoText(text = "사고기록하기", typoStyle = TypoStyle.BODY_LARGE_16)
}

@Preview
@Composable
fun RecordAccidentScreenPreview() {
    RecordAccidentRoute()
}