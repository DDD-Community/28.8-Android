package com.ddd.carssok.core.designsystem.component.input

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddd.carssok.core.designsystem.R
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.TypoText

@Composable
fun InputTextGroupBox(
    modifier: Modifier = Modifier,
    title: String,
    inputTextBoxes: List<@Composable () -> Unit> = emptyList(),
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
                    color = Color(0xFF979797),// TODO GroupBox Color 적용
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(vertical = 24.dp)
        ) {
            inputTextBoxes.forEach { inputTextBox ->
                // TODO GroupBox Color 적용
                inputTextBox()
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InputTextGroupPreview() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            InputTextGroupBox(
                title = "그룹 입력",
                inputTextBoxes = listOf(
                    {
                        InputTextBox(
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
                    },
                    {
                        InputTextBox(
                            title = "title2",
                            hintText = "텍스트을 입력해주세요",
                            state = InputState.DEFAULT,
                            intPutText = "",
                            onInputTextChange = {}
                        )
                    },
                    {
                        InputTextBox(
                            title = "title3",
                            state = InputState.COMPLETE,
                            intPutText = "input msg",
                            onInputTextChange = {}
                        )
                    },
                )
            )
        }
    }
}