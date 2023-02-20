package com.ddd.carssok.core.designsystem.component

import android.content.res.Configuration
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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
        colors = CarssokButtonDefaults.defaultColors(),
        shape = RoundedCornerShape(99.dp),
        onClick = { onClicked?.invoke() }) {
        TypoText(
            text = stringResource(id = titleRes),
            colorResource = R.color.white,
            typoStyle = TypoStyle.HEADLINE_X_SMALL_14
        )
    }
}

@Composable
fun CarssokIconButton(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    isEnabled: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    onClicked: (() -> Unit)? = null
) {
    Button(
        modifier = modifier,
        enabled = isEnabled,
        colors = CarssokButtonDefaults.defaultColors(),
        shape = RoundedCornerShape(99.dp),
        onClick = { onClicked?.invoke() }) {
        leadingIcon?.let {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                leadingIcon()

                TypoText(
                    text = stringResource(id = titleRes),
                    colorResource = R.color.white,
                    typoStyle = TypoStyle.HEADLINE_X_SMALL_14,
                )
            }
        } ?: run {
            TypoText(
                text = stringResource(id = titleRes),
                colorResource = R.color.white,
                typoStyle = TypoStyle.HEADLINE_X_SMALL_14,
            )
        }
    }
}

@Composable
fun CarssokOutlinedButton(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    isEnabled: Boolean = false,
    radius: Dp = 99.dp,
    colors: ButtonColors = CarssokButtonDefaults.outlinedColors(),
    border: BorderStroke = CarssokButtonDefaults.outlinedBorder(),
    @ColorRes textColorRes: Int = R.color.secondary_text,
    leadingIcon: @Composable (() -> Unit)? = null,
    onClicked: (() -> Unit)? = null,
) {
    OutlinedButton(
        modifier = modifier,
        enabled = isEnabled,
        colors = colors,
        shape = RoundedCornerShape(radius),
        border = border,
        onClick = { onClicked?.invoke() }
    ) {
        leadingIcon?.let {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                leadingIcon()

                TypoText(
                    text = stringResource(id = titleRes),
                    colorResource = textColorRes,
                    typoStyle = TypoStyle.HEADLINE_X_SMALL_14,
                    maxLines = 1,
                )
            }
        } ?: run {
            TypoText(
                text = stringResource(id = titleRes),
                colorResource = textColorRes,
                typoStyle = TypoStyle.HEADLINE_X_SMALL_14,
                maxLines = 1,
            )
        }
    }
}

object CarssokButtonDefaults {
    @Composable
    fun defaultColors(
        containerColor: Color = colorResource(id = R.color.button_enabled),
        contentColor: Color = colorResource(id = R.color.white),
        disabledContainerColor: Color = colorResource(id = R.color.button_disabled),
        disabledContentColor: Color = colorResource(id = R.color.white),
    ) = ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
    )

    @Composable
    fun outlinedColors(
        containerColor: Color = Color.Transparent,
        contentColor: Color = colorResource(id = R.color.divider_tertiary_),
        disabledContainerColor: Color = Color.Transparent,
        disabledContentColor: Color = colorResource(id = R.color.divider_tertiary_),
    ) = ButtonDefaults.outlinedButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
    )

    @Composable
    fun outlinedBorder(
        width: Dp = 1.dp,
        color: Color = colorResource(id = R.color.divider_tertiary_),
    ) = BorderStroke(
        width = width,
        color = color
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CarssokButtonPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
        CarssokOutlinedButton(
            titleRes = androidx.appcompat.R.string.abc_capital_off,
            radius = 10.dp,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Delete,
                    tint = colorResource(id = R.color.secondary_text),
                    contentDescription = null
                )
            },
        )
    }
}