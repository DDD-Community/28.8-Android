package com.ddd.carssok.core.designsystem.component.input

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddd.carssok.core.designsystem.R
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.TypoText

@Composable
fun InputTextGroupBox(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier.padding(vertical = 24.dp, horizontal = 24.dp)
    ) {
        TypoText(
            text = title,
            typoStyle = TypoStyle.HEADLINE_X_SMALL_14,
            modifier = modifier.padding(bottom = 12.dp)
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(12.dp))
                .background(
                    color = colorResource(id = R.color.secondary_bg),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(vertical = 24.dp)
        ) {
            content()
        }
    }
}

@Composable
fun InputTextBoxInGroup(
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

    InputTextBoxInternal(
        modifier = modifier,
        inputState = rememberInputState,
        title = title,
        titleRes = titleRes,
        errorSupportText = errorSupportText,
        importanceCount = importanceCount,
    ) {
        InputTextFieldInGroupInternal(
            inputState = rememberInputState,
            inputTextFiledEnabled = inputTextFiledEnabled,
            intPutText = intPutText,
            hintText = hintText,
            leadingIcon = leadingIcon,
            onInputTextChange = onInputTextChange,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputTextFieldInGroupInternal(
    modifier: Modifier = Modifier,
    inputState: InputState,
    inputTextFiledEnabled: Boolean = true,
    intPutText: String,
    hintText: String? = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    onInputTextChange: (String) -> Unit,
) {
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
        colors = InputTextDefaults.GroupBoxColors(state = inputState),
        isError = inputState == InputState.ERROR
    )
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
fun InputTextGroupPreview() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            InputTextGroupBox(
                title = "그룹 입력",
            ) {
                InputTextBoxInGroup(
                    errorSupportText = "휴대폰번호가 맞지 않습니다.",
                    title = "title1",
                    hintText = "텍스트을 입력해주세요",
                    state = InputState.ERROR,
                    intPutText = "input msg",
                    importanceCount = 2,
                    leadingIcon = {
                        Icon(painter = painterResource(id = R.drawable.ic_calendar_24), contentDescription = null)
                    },
                    onInputTextChange = {}
                )
                InputTextBoxInGroup(
                    title = "title2",
                    hintText = "텍스트을 입력해주세요",
                    state = InputState.DEFAULT,
                    intPutText = "",
                    onInputTextChange = {}
                )
                InputTextBoxInGroup(
                    title = "title3",
                    state = InputState.COMPLETE,
                    intPutText = "input msg",
                    onInputTextChange = {}
                )
            }
        }
    }
}