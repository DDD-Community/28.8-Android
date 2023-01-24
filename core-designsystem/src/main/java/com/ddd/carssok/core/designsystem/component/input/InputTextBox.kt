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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddd.carssok.core.designsystem.R
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.TypoText

enum class InputState {
    EMPTY, DEFAULT, COMPLETE, ERROR
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextBox(
    modifier: Modifier = Modifier.fillMaxWidth(),
    title: String? = null,
    @StringRes titleRes: Int? = null,
    inputTextFiledEnabled: Boolean = true,
    state: InputState = InputState.EMPTY,
    intPutText: String,
    hintText: String? = "",
    errorSupportText: String? = null,
    importanceCount: Int = 0,
    leadingIcon: @Composable (() -> Unit)? = null,
    onInputTextChange: (String) -> Unit,
) {
    val rememberInputState by remember {
        mutableStateOf(state)
    }
    Column(modifier = modifier.padding(vertical = 10.dp, horizontal = 24.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InputTextTitle(titleRes, title, importanceCount, rememberInputState)
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            enabled = inputTextFiledEnabled,
            modifier = modifier.fillMaxWidth(),
            value = intPutText,
            textStyle = TextStyle(fontWeight = TypoStyle.BODY_LARGE_16.fontWeight),
            onValueChange = onInputTextChange,
            singleLine = true,
            leadingIcon = leadingIcon,
            shape = RoundedCornerShape(8.dp),
            placeholder = {
                TypoText(
                    text = hintText.orEmpty(),
                    typoStyle = TypoStyle.BODY_MEDIUM_14,
                    color = colorResource(id = R.color.disable_text)
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = colorResource(id = R.color.primary_text),
                focusedBorderColor = colorResource(id = R.color.divider_secondary),
                unfocusedBorderColor = colorResource(id = R.color.divider_primary),
                errorBorderColor = colorResource(id = R.color.error_text),
                cursorColor = if (rememberInputState == InputState.ERROR) colorResource(id = R.color.error_text) else colorResource(
                    id = R.color.primary_text
                ),
                containerColor = if (rememberInputState == InputState.ERROR) colorResource(id = R.color.error_bg) else colorResource(
                    id = R.color.secondary_bg
                )
            ),
            isError = rememberInputState == InputState.ERROR
        )
        if (rememberInputState == InputState.ERROR && errorSupportText.isNullOrEmpty().not()) {
            TypoText(
                colorResource = R.color.error_text,
                text = errorSupportText.orEmpty(),
                typoStyle = TypoStyle.BODY_X11_SMALL
            )
        }
    }
}

@Composable
fun InputTextTitle(titleRes: Int?, title: String?, importanceCount: Int, state: InputState) {
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
    if (state != InputState.EMPTY) {
        Icon(
            painter = painterResource(id = R.drawable.ic_check_14),
            contentDescription = null,
            tint = colorResource(getInputTextHintImageTint(state))
        )
    }
}

fun getInputTextHintImageTint(state: InputState): Int {
    return when (state) {
        InputState.EMPTY -> -1
        InputState.DEFAULT -> R.color.divider_primary
        InputState.COMPLETE -> R.color.tertiary_text
        InputState.ERROR -> R.color.error_text
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InputTextPreview() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            InputTextBox(errorSupportText = "휴대폰번호가 맞지 않습니다.",
                title = "title1",
                hintText = "텍스트을 입력해주세요",
                state = InputState.ERROR,
                intPutText = "input msg",
                importanceCount = 2,
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.ic_calendar_24), contentDescription = null)
                },
                onInputTextChange = {

                })
            InputTextBox(title = "title2", hintText = "텍스트을 입력해주세요", state = InputState.DEFAULT, intPutText = "",
                onInputTextChange = {

                })
            InputTextBox(title = "title3", state = InputState.COMPLETE, intPutText = "input msg", onInputTextChange = {

            })
        }
    }
}