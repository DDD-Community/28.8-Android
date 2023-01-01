package com.ddd.carssok.feature.record.drive

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.TypoText

@Composable
fun RecordDriveRoute() {
    RecordDriveScreen()
}

@Composable
fun RecordDriveScreen() {
    TypoText(text = "주행기록하기", typoStyle = TypoStyle.BODY_LARGE_16)
}

@Preview
@Composable
fun RecordDriveScreenPreview() {
    RecordDriveRoute()
}