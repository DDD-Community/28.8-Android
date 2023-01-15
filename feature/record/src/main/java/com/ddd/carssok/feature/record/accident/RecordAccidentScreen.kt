@file:OptIn(ExperimentalMaterial3Api::class)

package com.ddd.carssok.feature.record.accident

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.Appbar
import com.ddd.carssok.core.designsystem.component.CarssokButton
import com.ddd.carssok.core.designsystem.component.TypoText
import com.ddd.carssok.core.designsystem.component.input.InputImageBox
import com.ddd.carssok.core.designsystem.component.input.InputTextBox
import com.ddd.carssok.feature.record.R

@Composable
fun RecordAccidentRoute(onClickedBack: () -> Unit) {
    RecordAccidentScreen(
        onClickedBack = onClickedBack,
        onClickedDataPicker = {}
    )
}

@Composable
fun RecordAccidentScreen(
    onClickedBack: () -> Unit,
    onClickedDataPicker: () -> Unit
) {
    Scaffold(
        topBar = {
            Appbar(
                backButtonImageResource = com.ddd.carssok.core.designsystem.R.drawable.ic_arrow_back_circle_32,
                onClickedBack = onClickedBack
            )
        },
        containerColor = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_bg),
        floatingActionButton = {
            CarssokButton(
                modifier = Modifier.size(width = 160.dp, height = 56.dp),
                titleRes = R.string.record_accident_save,
                isEnabled = true,
                onClicked = {

                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
        ) {
            item("record_accident_title") { RecordAccidentTitle() }
            item("record_accident_date") { RecordAccidentDate(onClickedDataPicker) }
            item("record_accident_location") { RecordAccidentLocation() }
            item("record_accident_gallery") { RecordAccidentGallery() }
            item("record_accident_memo") { RecordAccidentMemo() }
            item { Spacer(modifier = Modifier.height(96.dp)) }
        }
    }
}

@Composable
fun RecordAccidentTitle() {
    TypoText(
        modifier = Modifier.padding(top = 20.dp, bottom = 28.dp, start = 24.dp, end = 24.dp),
        text = stringResource(R.string.record_accident_title),
        typoStyle = TypoStyle.DISPLAY_SMALL_24
    )
}

@Composable
fun RecordAccidentDate(onClickedDataPicker: () -> Unit) {
    InputTextBox(
        modifier = Modifier.clickable { onClickedDataPicker() },
        title = stringResource(id = R.string.record_accident_timestamp_title),
        hintText = stringResource(R.string.record_accident_timestamp_hint),
        intPutText = "",
        importanceCount = 1,
        inputTextFiledEnabled = false,
        leadingIcon = {
            Icon(
                painter = painterResource(id = com.ddd.carssok.core.designsystem.R.drawable.ic_calendar_24),
                tint = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_text),
                contentDescription = null
            )
        },
        onInputTextChange = {

        }
    )
}

@Composable
fun RecordAccidentLocation() {
    InputTextBox(
        title = stringResource(id = R.string.record_accident_location_title),
        hintText = stringResource(R.string.record_accident_location_hint),
        intPutText = "",
        importanceCount = 1,
        onInputTextChange = {

        }
    )
}


@Composable
fun RecordAccidentMemo() {
    InputTextBox(
        title = stringResource(id = R.string.record_accident_memo_title),
        hintText = stringResource(R.string.record_accident_memo_hint),
        intPutText = "",
        importanceCount = 1,
        onInputTextChange = {

        }
    )
}

@Composable
fun RecordAccidentGallery() {
    InputImageBox(
        title = stringResource(id = R.string.record_accident_image),
        titleHint = stringResource(id = R.string.record_accident_image_upload_max),
        cardTitle = stringResource(id = R.string.record_accident_image_upload_add),
        cardIconRes = com.ddd.carssok.core.designsystem.R.drawable.ic_calendar_24,
        saveList = listOf(1, 2, 3, 4),
        onClickedRemove = {

        },
        onClickedAdd = {

        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RecordAccidentScreenPreview() {
    RecordAccidentRoute(
        onClickedBack = {

        }
    )
}