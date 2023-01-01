package com.ddd.carssok.feature.record.maintenance

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.TypoText

@Composable
fun RecordMaintenanceRoute() {
    RecordMaintenanceScreen()
}

@Composable
fun RecordMaintenanceScreen() {
    TypoText(text = "정비기록하기", typoStyle = TypoStyle.BODY_LARGE_16)
}

@Preview
@Composable
fun RecordMaintenanceScreenPreview() {
    RecordMaintenanceRoute()
}