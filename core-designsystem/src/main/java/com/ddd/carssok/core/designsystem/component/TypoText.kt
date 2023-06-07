package com.ddd.carssok.core.designsystem.component

import android.content.res.Configuration
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.ddd.carssok.core.designsystem.R
import com.ddd.carssok.core.designsystem.TypoStyle

@Composable
fun TypoText(
    modifier: Modifier? = Modifier,
    text: String = "",
    @StringRes textResource: Int? = null,
    color: Color = colorResource(id = R.color.primary_text),
    @ColorRes colorResource: Int? = null,
    typoStyle: TypoStyle,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textDecoration: TextDecoration? = null,
) {
    Text(
        modifier = modifier ?: Modifier,
        text = if (textResource != null) stringResource(id = textResource) else text,
        fontSize = with(LocalDensity.current) { Dp(typoStyle.textSize.toFloat()).toSp() },
        color = if (colorResource != null) colorResource(id = colorResource) else color,
        fontWeight = typoStyle.fontWeight,
        textAlign = textAlign,
        lineHeight = lineHeight,
        maxLines = maxLines,
        overflow = overflow,
        textDecoration = textDecoration
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TypeTextPreview() {
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        TypoStyle.values().forEach {
            TypoText(text = it.name, typoStyle = it)
        }
    }
}