package com.ddd.carssok.feature.record.refuel

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.TypoText

@Composable
fun RecordRefuelRoute() {
    RecordRefuelScreen()
}

@Composable
fun RecordRefuelScreen() {
    TypoText(text = "주유기록하기", typoStyle = TypoStyle.BODY_LARGE_16)
}

@Preview
@Composable
fun RecordRefuelScreenPreview() {
    RecordRefuelRoute()
}