package com.ddd.carssok.core.designsystem.component.input

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ddd.carssok.core.designsystem.R
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.CarssokRadioButton
import com.ddd.carssok.core.designsystem.component.TypoText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextDropDownBox(
    modifier: Modifier = Modifier.fillMaxWidth(),
    colors: TextFieldColors = InputTextDefaults.DropDownColors(),
    title: String? = null,
    @StringRes titleRes: Int? = null,
    importanceCount: Int = 0,
    leadingIcon: @Composable (() -> Unit)? = null,
    suggestions: List<String>,
    defaultSuggestionIndex: Int = 0,
    onSelectedItem: (Int) -> Unit,
) {
    var rememberSelectedIndex by remember { mutableStateOf(defaultSuggestionIndex) }
    var rememberDropDownExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.padding(vertical = 10.dp, horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InputTextDropDownTitle(titleRes, title, importanceCount)
        }
        Spacer(modifier = Modifier.height(10.dp))

        ExposedDropdownMenuBox(
            expanded = rememberDropDownExpanded,
            onExpandedChange = {
                rememberDropDownExpanded = !rememberDropDownExpanded
            }
        ) {
            OutlinedTextField(
                readOnly = true,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                value = suggestions[rememberSelectedIndex],
                textStyle = TextStyle(fontWeight = TypoStyle.BODY_LARGE_16.fontWeight),
                onValueChange = {},
                singleLine = true,
                leadingIcon = leadingIcon,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = rememberDropDownExpanded
                    )
                },
                shape = RoundedCornerShape(8.dp),
                placeholder = {
                    TypoText(
                        text = suggestions[rememberSelectedIndex],
                        typoStyle = TypoStyle.BODY_MEDIUM_14,
                        color = colorResource(id = R.color.disable_text)
                    )
                },
                colors = colors,
            )
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme.copy(
                    surface = Color.Transparent,
                ),
                shapes = MaterialTheme.shapes.copy(
                    extraSmall = RoundedCornerShape(12.dp),
                )
            ) {
                DropdownMenu(
                    expanded = rememberDropDownExpanded,
                    onDismissRequest = {
                        rememberDropDownExpanded = false
                    },
                    modifier = Modifier
                        .heightIn(max = MaxDropDownMenuHeightDp)
                        .exposedDropdownSize()
                        .background(color = colorResource(id = R.color.primary_bg)),
                ) {
                    suggestions.forEachIndexed { index, label ->
                        DropdownMenuItem(
                            modifier = Modifier
                                .height(DropDownMenuItemHeightDp)
                                .background(
                                    color = if (rememberSelectedIndex == index)
                                        colorResource(id = R.color.tertiary_bg)
                                    else
                                        colorResource(id = R.color.primary_bg)
                                ),
                            colors = MenuDefaults.itemColors(
                                textColor = if (rememberSelectedIndex == index)
                                    colorResource(id = R.color.white)
                                else
                                    colorResource(id = R.color.secondary_text),
                                leadingIconColor = colorResource(id = R.color.button_enabled),
                                trailingIconColor = colorResource(id = R.color.button_enabled),
                                disabledTextColor = if (rememberSelectedIndex == index)
                                    colorResource(id = R.color.white)
                                else
                                    colorResource(id = R.color.secondary_text),
                                disabledLeadingIconColor = colorResource(id = R.color.button_disabled),
                                disabledTrailingIconColor = colorResource(id = R.color.button_disabled),
                            ),
                            leadingIcon = {
                                CarssokRadioButton(
                                    selected = rememberSelectedIndex == index,
                                    onClick = {},
                                )
                            },
                            text = {
                                Text(text = label)
                            },
                            onClick = {
                                rememberSelectedIndex = index
                                rememberDropDownExpanded = false
                                onSelectedItem(index)
                            }
                        )
                    }
                }
            }
        }
    }
}

private const val dropDownMenuItemHeight = 48f
private const val materialDropdownMenuVerticalPadding = 8f
private val DropDownMenuItemHeightDp = Dp(dropDownMenuItemHeight)
private val MaxDropDownMenuHeightDp = Dp(dropDownMenuItemHeight * 5 + materialDropdownMenuVerticalPadding * 2)

@Composable
private fun InputTextDropDownTitle(titleRes: Int?, title: String?, importanceCount: Int) {
    Row {
        TypoText(
            text = titleRes?.let { stringResource(id = it) } ?: kotlin.run { title.orEmpty() },
            typoStyle = TypoStyle.HEADLINE_X_SMALL_14
        )
        if (importanceCount != 0) {
            repeat(importanceCount) {
                Box {
                    TypoText(
                        modifier = Modifier.padding(start = 1.dp),
                        text = "*",
                        typoStyle = TypoStyle.HEADLINE_X_SMALL_14,
                        color = colorResource(id = R.color.tertiary_text),
                    )
                }
            }
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
fun InputTextDropDownPreview() {
    InputTextDropDownBox(
        title = "title1",
        importanceCount = 1,
        suggestions = listOf(
            "11111",
            "22222",
            "33333"
        ),
        onSelectedItem = {},
    )
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
fun InputTextDropDownInGroupPreview() {
    InputTextGroupBox(title = "GroupBox sample") {
        // dropdown
        InputTextDropDownBox(
            colors = InputTextDefaults.DropDownInGroupColors(),
            title = "dropdown in group sample",
            importanceCount = 1,
            suggestions = listOf(
                "11111",
                "22222",
                "33333",
                "44444",
                "55555",
                "66666",
                "77777",
                "88888",
                "99999",
            ),
            onSelectedItem = {

            }
        )
        InputTextBoxInGroup(
            title = "textField in group",
            hintText = "textField in group hint",
            intPutText = "",
            importanceCount = 1,
            onInputTextChange = {},
        )
    }
}