package com.ddd.carssok.core.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.ddd.carssok.core.designsystem.R

@Composable
fun CarssokRadioButton(
    selected: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    RadioButton(
        selected = selected,
        enabled = enabled,
        colors = RadioButtonDefaults.colors(
            selectedColor = colorResource(id = R.color.button_hovered),
            unselectedColor = colorResource(id = R.color.divider_tertiary_),
            disabledSelectedColor = colorResource(id = R.color.button_enabled),
            disabledUnselectedColor = colorResource(id = R.color.button_disabled)
        ),
        onClick = onClick
    )
}

@Preview
@Composable
fun CarssokRadioButtonPreview() {
    Column {
        CarssokRadioButton(selected = true, enabled = true, onClick = {})
        CarssokRadioButton(selected = true, enabled = false, onClick = {})
        CarssokRadioButton(selected = false, enabled = true, onClick = {})
        CarssokRadioButton(selected = false, enabled = false, onClick = {})
    }
}