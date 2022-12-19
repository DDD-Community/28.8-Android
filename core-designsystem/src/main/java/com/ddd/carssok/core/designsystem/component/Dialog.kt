package com.ddd.carssok.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.R

/**
 * @param confirmButtonTitle    확인 버튼 타이틀
 * @param onConfirmClicked      확인 버튼 클릭시의 동작
 * @param dismissButtonTitle    취소 버튼 타이틀
 * @param onDismissClicked      버튼 클릭시의 동작
 *                              null이 아닐때 취소 버튼 표시 및
 * @param onDismissRequest      다이얼로그 종료 요청이 왔을 때의 처리
 * @param properties            다이얼로그 속성 (종료 규칙, 보안 규칙 등)
 */
@Composable
fun CommonDialog(
    modifier: Modifier = Modifier,
    title: String = "타이틀",
    text: String = "A dialog is a type of modal\nfront of app content to ",
    confirmButtonTitle: String = stringResource(id = R.string.dialog_confirm_yes),
    onConfirmClicked: () -> Unit,
    dismissButtonTitle: String? = null,
    onDismissClicked: (() -> Unit)? = null,
    onDismissRequest: () -> Unit = {}, // cancel 동작
    properties: DialogProperties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        CommonDialogContent(
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(ButtonsPadding)
                ) {
                    onDismissClicked?.let {
                        Box(
                            modifier = modifier
                                .weight(1f)
                                .padding(vertical = 16.dp)
                                .clickable { onDismissClicked() }
                        ) {
                            TypoText(
                                text = dismissButtonTitle ?: "아니요",
                                typoStyle = TypoStyle.HEADLINE_X_SMALL_14,
                                color = colorResource(id = R.color.secondary_text),
                                modifier = modifier.align(Alignment.Center)
                            )
                        }
                    }
                    Box(
                        modifier = modifier
                            .weight(1f)
                            .padding(vertical = 16.dp)
                            .clickable { onConfirmClicked() }
                    ) {
                        TypoText(
                            text = confirmButtonTitle,
                            typoStyle = TypoStyle.HEADLINE_X_SMALL_14,
                            color = colorResource(id = R.color.tertiary_text),
                            modifier = modifier.align(Alignment.Center)
                        )
                    }
                }
            },
            title = {
                Box(modifier = modifier
                    .fillMaxWidth()
                    .padding(TitlePadding)) {
                    TypoText(
                        text = title,
                        typoStyle = TypoStyle.HEADLINE_LARGE_20,
                        color = colorResource(id = R.color.primary_text),
                        modifier = modifier.align(Alignment.Center)
                    )
                }
            },
            text = {
                Box(modifier = modifier
                    .fillMaxWidth()
                    .padding(TextPadding)) {
                    TypoText(
                        text = text,
                        typoStyle = TypoStyle.HEADLINE_X_SMALL_14,
                        color = colorResource(id = R.color.secondary_text),
                        modifier = modifier.align(Alignment.Center)
                    )
                }
            }
        )
    }

}

@Composable
fun CommonDialogContent(
    buttons: @Composable () -> Unit,
    title: (@Composable () -> Unit)?,
    text: (@Composable () -> Unit)?,
    modifier: Modifier = Modifier,
    shape: Shape = AlertDialogDefaults.shape,
    containerColor: Color = colorResource(id = R.color.primary_bg),
    tonalElevation: Dp = AlertDialogDefaults.TonalElevation,
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = containerColor,
        tonalElevation = tonalElevation
    ) {
        Column(modifier = modifier.padding(DialogPadding)) {
            title?.let {
                title()
            }
            text?.let {
                text()
            }
            buttons()
        }
    }
}

private val DialogPadding = PaddingValues(top = 28.dp, start = 20.dp, bottom = 20.dp, end = 20.dp)
private val TitlePadding = PaddingValues(bottom = 18.dp)
private val TextPadding = PaddingValues()
private val ButtonsPadding = PaddingValues(top = 20.dp)

@Preview
@Composable
fun DialogPreview() {
    CommonDialog(
        confirmButtonTitle = stringResource(id = R.string.dialog_confirm_yes),
        onConfirmClicked = {},
        dismissButtonTitle = stringResource(id = R.string.dialog_dismiss_no),
        onDismissClicked = {}
    )
}