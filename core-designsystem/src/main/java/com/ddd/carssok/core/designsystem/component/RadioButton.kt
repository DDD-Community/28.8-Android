package com.ddd.carssok.core.designsystem.component

import android.content.res.Configuration
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
            selectedColor = colorResource(id = R.color.button_enabled),
            unselectedColor = colorResource(id = R.color.button_disabled),
            disabledSelectedColor = colorResource(id = R.color.button_enabled),
            disabledUnselectedColor = colorResource(id = R.color.button_disabled)
        ),
        onClick = onClick
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CarssokRadioButtonPreview() {
    Column {
        CarssokRadioButton(selected = true, enabled = true, onClick = {})
        CarssokRadioButton(selected = false, enabled = true, onClick = {})
    }
}