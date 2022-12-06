package com.ddd.carssok.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddd.carssok.core.designsystem.R
import com.ddd.carssok.core.designsystem.TypoStyle


@Composable
fun CarssokButton(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    isEnabled: Boolean = false,
    onClicked: (() -> Unit)? = null
) {
    Button(
        modifier = modifier,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.button_enabled),
            contentColor = colorResource(id = R.color.white),
            disabledContainerColor = colorResource(id = R.color.button_disabled),
            disabledContentColor = colorResource(id = R.color.white)
        ),
        shape = RoundedCornerShape(12.dp),
        onClick = { onClicked?.invoke() }) {
        TypoText(
            text = stringResource(id = titleRes),
            colorResource = R.color.white,
            typoStyle = TypoStyle.HEADLINE_X_SMALL_14
        )
    }
}

@Preview
@Composable
fun CarssokButtonPreview() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        CarssokButton(
            titleRes = androidx.appcompat.R.string.abc_capital_off,
            isEnabled = true,
            modifier = Modifier.fillMaxWidth()
        )
        CarssokButton(
            titleRes = androidx.appcompat.R.string.abc_capital_off,
            isEnabled = false,
            modifier = Modifier.fillMaxWidth()
        )
    }
}