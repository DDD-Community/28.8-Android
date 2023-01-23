package com.ddd.carssok.feature.record.drive

import RecordDriveBackHandler
import RecordDriveSaveDialog
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.Appbar
import com.ddd.carssok.core.designsystem.component.CarssokButton
import com.ddd.carssok.core.designsystem.component.TypoText
import com.ddd.carssok.core.designsystem.component.input.InputTextBox
import com.ddd.carssok.feature.record.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun RecordDriveRoute(
    viewModel: RecordDriveViewModel = hiltViewModel(),
    onClickedBack: () -> Unit
) {
    RecordDriveScreen(
        mileageState = viewModel.mileageState,
        onInputMileageChanged = viewModel::updateMileage,
        onClickedSave = {},
        onClickedPreviousDrivingHistory = {},
        onClickedBack = onClickedBack
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordDriveScreen(
    modifier: Modifier = Modifier,
    mileageState: StateFlow<String>,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    saveDialogState: MutableState<Boolean> = remember { mutableStateOf(false) },
    onInputMileageChanged: (String) -> Unit,
    onClickedSave: () -> Unit,
    onClickedPreviousDrivingHistory: () -> Unit,
    onClickedBack: () -> Unit,
) {
    val mileage by mileageState.collectAsState()

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
                modifier = modifier
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
        Column(
            modifier = Modifier.padding(padding)
        ) {
            RecordDriveTitle(
                modifier = modifier.padding(top = 28.dp, start = 24.dp, bottom = 0.dp, end = 24.dp)
            )

            RecordDriveMileage(
                modifier = modifier.padding(top = 12.dp, start = 24.dp, bottom = 0.dp, end = 24.dp)
            )

            RecordDriveSubTitle(
                modifier = modifier.padding(top = 52.dp, start = 24.dp, bottom = 24.dp, end = 24.dp)
            )

            RecordDriveInputDate()

            RecordDriveInputMileage(
                mileage = mileage,
                onValueChanged = {
                    rememberSaveButtonEnabled = it.isNotBlank()
                    onInputMileageChanged(it)
                }
            )

            RecordDrivePreviousDrivingHistory(
                onClicked = onClickedPreviousDrivingHistory,
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 31.2.dp),
            )
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
        Spacer(modifier = modifier.size(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TypoText(
                text = stringResource(id = R.string.record_drive_main_title2),
                typoStyle = TypoStyle.DISPLAY_SMALL_24
            )
            Image(
                painter = painterResource(id = com.ddd.carssok.core.designsystem.R.drawable.ic_record_drive_car),
                contentDescription = null
            )
        }
    }
}

@Composable
fun RecordDriveMileage(
    mileage: Int = 0,
    modifier: Modifier = Modifier
) {
    val colorResource = if(mileage > 0)
            com.ddd.carssok.core.designsystem.R.color.tertiary_text
        else
            com.ddd.carssok.core.designsystem.R.color.disable_text

    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = modifier
    ) {
        TypoText(
            text = mileage.toString(),
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
    modifier: Modifier = Modifier
) {
    InputTextBox(
        modifier = modifier,
        title = stringResource(id = R.string.record_drive_input_date_title),
        hintText = stringResource(id = R.string.record_drive_input_date_hint),
        intPutText = "",
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
fun RecordDriveInputMileage(
    modifier: Modifier = Modifier,
    mileage: String,
    onValueChanged: (String) -> Unit,
) {
    InputTextBox(
        title = stringResource(id = R.string.record_drive_input_mileage_title),
        hintText = stringResource(id = R.string.record_drive_input_mileage_hint),
        intPutText = mileage,
        importanceCount = 1,
        modifier = modifier,
        onInputTextChange = {
            onValueChanged(it)
        }
    )
}

@Composable
fun RecordDrivePreviousDrivingHistory(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit,
) {
    TypoText(
        text = stringResource(id = R.string.record_drive_show_previous_driving_history),
        typoStyle = TypoStyle.BODY_MEDIUM_14,
        textDecoration = TextDecoration.Underline,
        modifier = modifier.clickable {
            onClicked()
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RecordDrivePreview() {
    RecordDriveScreen(
        mileageState = MutableStateFlow<String>(""),
        onInputMileageChanged = {},
        onClickedSave = {},
        onClickedPreviousDrivingHistory = {},
        onClickedBack = {}
    )
}