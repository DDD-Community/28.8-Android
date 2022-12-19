package com.ddd.carssok.feature.record

import androidx.compose.runtime.Composable
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.TypoText

@Composable
fun RecordRoute() {
    RecordScreen()
}

@Composable
fun RecordScreen() {
    TypoText(text = "기록하기", typoStyle = TypoStyle.BODY_LARGE_16)
}

@Composable
fun RecordScreenPreview() {
    RecordRoute()
}