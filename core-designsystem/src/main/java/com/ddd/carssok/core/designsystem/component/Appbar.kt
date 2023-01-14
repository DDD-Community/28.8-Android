package com.ddd.carssok.core.designsystem.component

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ddd.carssok.core.designsystem.R
import com.ddd.carssok.core.designsystem.TypoStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Appbar(
    @StringRes titleRes: Int? = null,
    @DrawableRes backButtonImageResource: Int? = null,
    menuImageResource: Painter? = null,
    onClickedBack: (() -> Unit)? = null,
    onClickedMenuItem: (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        colors = centerAlignedTopAppBarColors(
            containerColor = colorResource(id = R.color.primary_bg)
        ),
        title = {
            if (titleRes != null) {
                TypoText(
                    text = stringResource(id = titleRes),
                    color = colorResource(id = R.color.primary_text),
                    textAlign = TextAlign.Center,
                    typoStyle = TypoStyle.HEADLINE_SMALL_16
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { onClickedBack?.invoke() }) {
                backButtonImageResource?.let {
                    Image(
                        painter = painterResource(id = it),
                        null,
                    )
                } ?: run {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back_24),
                        null,
                        tint = colorResource(id = R.color.primary_text)
                    )
                }

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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppbarPreview() {
    Scaffold(
        topBar = {
            Appbar(titleRes = androidx.appcompat.R.string.abc_capital_off)
        }
    ) {
        Column(modifier = Modifier.padding(it)) {

        }
    }
}