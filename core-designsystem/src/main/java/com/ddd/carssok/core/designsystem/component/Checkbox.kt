package com.ddd.carssok.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddd.carssok.core.designsystem.R

@Composable
fun CarssockCheckbox(isChecked: Boolean = false, onChangedValue: (Boolean) -> Unit) {
    var rememberCheckState by remember { mutableStateOf(isChecked) }
    Card(
        modifier = Modifier
            .size(16.dp)
            .clickable {
                rememberCheckState = rememberCheckState.not()
                onChangedValue.invoke(rememberCheckState.not())
            },
        shape = RoundedCornerShape(99.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(
                id = if (rememberCheckState) R.color.primary_bg else android.R.color.transparent
            )
        ),
        border = BorderStroke(if (rememberCheckState) 0.dp else 1.dp, colorResource(id = R.color.divider_primary))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement =
            Arrangement.Center
        ) {
            if (rememberCheckState) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check_6),
                    contentDescription = null,
                    tint = colorResource(id = R.color.button_hovered)
                )
            }
        }
    }
}

@Preview
@Composable
fun CarssockCheckboxPreview() {
    CarssockCheckbox(false) { isChecked ->

    }
}