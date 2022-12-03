package com.ddd.carssok.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.ddd.carssok.core.designsystem.R
import com.ddd.carssok.core.designsystem.TypoStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Appbar(
    @StringRes titleRes: Int,
    menuImageResource: Painter? = null,
    onClickedBack: (() -> Unit)? = null,
    onClickedMenuItem: (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.background(colorResource(id = R.color.primary_bg)),
        title = {
            TypoText(
                text = stringResource(id = titleRes),
                color = colorResource(id = R.color.primary_text),
                textAlign = TextAlign.Center,
                typoStyle = TypoStyle.HEADLINE_SMALL_16
            )
        },
        navigationIcon = {
            IconButton(onClick = { onClickedBack?.invoke() }) {
                Icon(painter = painterResource(id = R.drawable.ic_arrow_back_24), null)
            }
        },
        actions = {
            menuImageResource?.let {
                IconButton(onClick = { onClickedMenuItem?.invoke() }) {
                    Icon(painter = it, null)
                }
            }
        }
    )
}