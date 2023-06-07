package com.ddd.carssok.core.designsystem.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.ddd.carssok.core.designsystem.R

@Composable
fun Progress() {
    CircularProgressIndicator(color = colorResource(id = R.color.tertiary_bg))
}

@Preview
@Composable
fun ProgressPreview() {
    Progress()
}