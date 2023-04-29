package com.ddd.carssok.core.designsystem

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddd.carssok.core.designsystem.component.CarssokButton
import com.ddd.carssok.core.designsystem.component.TypoText

@Composable
fun ErrorScreen(onClickedRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.ic_network_error), contentDescription = null)
        Spacer(modifier = Modifier.height(20.dp))
        TypoText(textResource = R.string.network_title, typoStyle = TypoStyle.HEADLINE_LARGE_20)
        Spacer(modifier = Modifier.height(18.dp))
        TypoText(textResource = R.string.network_subtitle, typoStyle = TypoStyle.HEADLINE_LARGE_20)
        Spacer(modifier = Modifier.height(44.dp))
        CarssokButton(titleRes = R.string.retry, isEnabled = true, onClicked = onClickedRetry)
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen {

    }
}