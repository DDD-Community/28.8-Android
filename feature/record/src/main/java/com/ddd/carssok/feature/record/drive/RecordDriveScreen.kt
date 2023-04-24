package com.ddd.carssok.feature.record.drive

import RecordDriveBackHandler
import RecordDriveSaveDialog
import android.app.DatePickerDialog
import android.content.res.Configuration
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.Appbar
import com.ddd.carssok.core.designsystem.component.CarssokButton
import com.ddd.carssok.core.designsystem.component.CarssokOutlinedButton
import com.ddd.carssok.core.designsystem.component.DatePickerState
import com.ddd.carssok.core.designsystem.component.TypoText
import com.ddd.carssok.core.designsystem.component.input.InputTextBox
import com.ddd.carssok.core.designsystem.component.rememberDatePicker
import com.ddd.carssok.feature.record.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RecordDriveRoute(
    viewModel: RecordDriveViewModel = hiltViewModel(),
    navigateToPreviousHistory: () -> Unit,
    onClickedBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    RecordDriveScreen(
        uiState = uiState,
        onInputDistanceChanged = viewModel::updateDistance,
        onInputDateChanged = viewModel::updateDate,
        onClickedSave = viewModel::recordDriveHistory,
        onClickedPreviousHistory = navigateToPreviousHistory,
        onClickedBack = onClickedBack
    )

    LaunchedEffect(Unit) {
        viewModel.init()
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordDriveScreen(
    uiState: RecordDriveUiState,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    datePickerState: DatePickerState = rememberDatePicker(LocalContext.current),
    saveDialogState: MutableState<Boolean> = remember { mutableStateOf(false) },
    onInputDistanceChanged: (String) -> Unit,
    onInputDateChanged: (String) -> Unit,
    onClickedSave: () -> Unit,
    onClickedPreviousHistory: () -> Unit,
    onClickedBack: () -> Unit,
) {
    var rememberSaveButtonEnabled by remember { mutableStateOf(false) }

    RecordDriveBackHandler(
        enable = rememberSaveButtonEnabled,
        navigateUp = onClickedBack,
        coroutineScope = coroutineScope
    )

    RecordDriveSaveDialog(
        dialogState = saveDialogState,
        onClickedConfirm = onClickedSave
    )

    datePickerState.onDateSet { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
        onInputDateChanged(
            "$selectedYear-${"%02d".format(selectedMonth + 1)}-${"%02d".format(selectedDayOfMonth)}"
        )
    }

    Scaffold(
        topBar = {
            Appbar(
                titleRes = R.string.record_drive_app_bar_title,
                backButtonImageResource = com.ddd.carssok.core.designsystem.R.drawable.ic_arrow_back_circle_32,
                onClickedBack = onClickedBack
            )
        },
        containerColor = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_bg),
        floatingActionButton = {
            CarssokButton(
                titleRes = R.string.record_drive_save_button_title,
                isEnabled = rememberSaveButtonEnabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 24.dp),
                onClicked = {
                    coroutineScope.launch {
                        saveDialogState.value = true
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            horizontalAlignment = Alignment.Start
        ) {
            item {
                RecordDriveTitle(
                    modifier = Modifier.padding(top = 28.dp, start = 24.dp, bottom = 0.dp, end = 24.dp)
                )
            }

            item {
                RecordDriveDistance(
                    modifier = Modifier.padding(top = 12.dp, start = 24.dp, bottom = 0.dp, end = 24.dp),
                    distance = uiState.totalDistance,
                )
            }

            item {
                RecordDriveSubTitle(
                    modifier = Modifier.padding(top = 52.dp, start = 24.dp, bottom = 24.dp, end = 24.dp)
                )
            }

            item {
                RecordDriveInputDate(
                    modifier = Modifier.clickable {
                        datePickerState.updateDate(date = uiState.date)
                        datePickerState.show()
                    },
                    date = uiState.date
                )
            }

            item {
                RecordDriveInputDistance(
                    distance = uiState.distance,
                    onValueChanged = {
                        rememberSaveButtonEnabled = it.isNotBlank()
                        onInputDistanceChanged(it)
                    }
                )
            }

            item {
                RecordDrivePreviousHistory(
                    onClicked = onClickedPreviousHistory,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                        .padding(top = 31.2.dp),
                )
            }
        }
    }
}

@Composable
fun RecordDriveTitle(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        TypoText(
            text = stringResource(id = R.string.record_drive_main_title1),
            typoStyle = TypoStyle.DISPLAY_SMALL_24
        )
        Spacer(modifier = Modifier.size(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TypoText(
                text = stringResource(id = R.string.record_drive_main_title2),
                typoStyle = TypoStyle.DISPLAY_SMALL_24
            )
            Image(
                painter = painterResource(id = R.drawable.ic_record_drive_car),
                contentDescription = null
            )
        }
    }
}

@Composable
fun RecordDriveDistance(
    modifier: Modifier = Modifier,
    distance: Int = 0,
) {
    val colorResource = if(distance > 0)
            com.ddd.carssok.core.designsystem.R.color.tertiary_text
        else
            com.ddd.carssok.core.designsystem.R.color.disable_text

    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = modifier
    ) {
        TypoText(
            text = distance.toString(),
            typoStyle = TypoStyle.DISPLAY_X_LARGE_44,
            colorResource = colorResource
        )
        TypoText(
            text = "km",
            typoStyle = TypoStyle.DISPLAY_LARGE_32,
            colorResource = colorResource
        )
    }
}

@Composable
fun RecordDriveSubTitle(
    modifier: Modifier = Modifier
) {
    TypoText(
        text = stringResource(id = R.string.record_drive_sub_title),
        typoStyle = TypoStyle.HEADLINE_MEDIUM_18,
        modifier = modifier
    )
}

@Composable
fun RecordDriveInputDate(
    modifier: Modifier = Modifier,
    date: String,
) {
    InputTextBox(
        modifier = modifier,
        title = stringResource(id = R.string.record_drive_input_date_title),
        hintText = stringResource(id = R.string.record_drive_input_date_hint),
        intPutText = date,
        inputTextFiledEnabled = false,
        importanceCount = 1,
        leadingIcon = {
            Icon(
                painter = painterResource(id = com.ddd.carssok.core.designsystem.R.drawable.ic_calendar_24),
                tint = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_text),
                contentDescription = null
            )
        },
        onInputTextChange = {}
    )
}

@Composable
fun RecordDriveInputDistance(
    modifier: Modifier = Modifier,
    distance: String,
    onValueChanged: (String) -> Unit,
) {
    InputTextBox(
        title = stringResource(id = R.string.record_drive_input_distance_title),
        hintText = stringResource(id = R.string.record_drive_input_distance_hint),
        intPutText = distance,
        importanceCount = 1,
        modifier = modifier,
        onInputTextChange = {
            onValueChanged(it)
        }
    )
}

@Composable
fun RecordDrivePreviousHistory(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit,
) {
    CarssokOutlinedButton(
        modifier = modifier,
        titleRes = R.string.record_drive_show_previous_driving_history,
        radius = 10.dp,
        leadingIcon = {
            Icon(
                modifier = Modifier.padding(end = 4.dp),
                painter = painterResource(id = com.ddd.carssok.core.designsystem.R.drawable.ic_previous_time_18),
                tint = colorResource(id = com.ddd.carssok.core.designsystem.R.color.secondary_text),
                contentDescription = null
            )
        },
        isEnabled = true,
        onClicked = onClicked,
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RecordDrivePreview() {
    RecordDriveScreen(
        uiState = RecordDriveUiState.EMPTY,
        onInputDistanceChanged = {},
        onInputDateChanged = {},
        onClickedSave = {},
        onClickedPreviousHistory = {},
        onClickedBack = {}
    )
}