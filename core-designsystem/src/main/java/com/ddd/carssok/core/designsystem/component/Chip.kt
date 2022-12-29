package com.ddd.carssok.core.designsystem.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddd.carssok.core.designsystem.R
import com.ddd.carssok.core.designsystem.TypoStyle


@Composable
fun Chip(
    text: String,
    @StringRes textRes: Int? = null,
    isSelected: Boolean = false,
    modifier: Modifier = Modifier,
    onClicked: (Boolean) -> Unit
) {
    var selectedRemember by remember { mutableStateOf(isSelected) }
    Button(
        onClick = {
            selectedRemember = selectedRemember.not()
            onClicked.invoke(selectedRemember.not())
        },
        contentPadding = PaddingValues(vertical = 15.dp),
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = if (selectedRemember) R.color.tertiary_bg else R.color.secondary_bg)
        ),
        content = {
            TypoText(
                color = colorResource(id = if (selectedRemember) R.color.primary_text else R.color.secondary_text),
                textAlign = TextAlign.Center,
                text = if (textRes != null) stringResource(id = textRes) else text,
                typoStyle = TypoStyle.HEADLINE_X_SMALL_14
            )
        }
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview
@Composable
fun ChipPreview() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Chip("체크 안될때", isSelected = true) {

        }
        Chip("체크될때", isSelected = false) {

        }
    }
}