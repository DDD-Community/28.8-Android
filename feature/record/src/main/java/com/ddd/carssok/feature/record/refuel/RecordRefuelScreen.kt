package com.ddd.carssok.feature.record.refuel

import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.Appbar
import com.ddd.carssok.core.designsystem.component.CarssokButton
import com.ddd.carssok.core.designsystem.component.DatePickerState
import com.ddd.carssok.core.designsystem.component.TypoText
import com.ddd.carssok.core.designsystem.component.input.InputTextBox
import com.ddd.carssok.core.designsystem.component.input.InputTextGroupBox
import com.ddd.carssok.core.designsystem.component.input.InputTextBoxInGroup
import com.ddd.carssok.core.designsystem.component.rememberDatePicker
import com.ddd.carssok.feature.record.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RecordRefuelRoute(
    id: Int,
    viewModel: RecordRefuelViewModel = hiltViewModel(),
    onClickedBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    RecordRefuelScreen(
        uiState = uiState,
        onInputDateChanged = viewModel::updateInputDate,
        onInputDataChanged = viewModel::updateInputData,
        onClickedSave = viewModel::recordRefuelHistory,
        onClickedBack = onClickedBack,
    )

    LaunchedEffect(Unit) {
        viewModel.initialize(id)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordRefuelScreen(
    uiState: RecordRefuelUiState,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    scrollState: ScrollState = rememberScrollState(),
    datePickerState: DatePickerState = rememberDatePicker(LocalContext.current),
    onInputDateChanged: (String) -> Unit,
    onInputDataChanged: (RecordRefuelUiState.InputData) -> Unit,
    onClickedSave: () -> Unit,
    onClickedBack: () -> Unit,
) {
    datePickerState.onDateSet { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
        onInputDateChanged(
            "$selectedYear-${"%02d".format(selectedMonth + 1)}-$selectedDayOfMonth"
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            Appbar(
                backButtonImageResource = com.ddd.carssok.core.designsystem.R.drawable.ic_arrow_back_circle_32,
                onClickedBack = onClickedBack
            )
        },
        containerColor = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_bg),
        floatingActionButton = {
            if (uiState.isInputMode) {
                CarssokButton(
                    titleRes = R.string.record_refuel_save_button_title,
                    isEnabled = uiState.isSaveButtonEnable,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 24.dp),
                    onClicked = onClickedSave
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { padding ->

        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            item {
                RecordRefuelTitle(
                    modifier = Modifier.padding(top = 20.dp, start = 24.dp, bottom = 28.dp, end = 24.dp)
                )
            }

            item {
                RecordRefuelInputDate(
                    modifier = if (uiState.isInputMode) {
                        Modifier.clickable {
                            if (uiState.inputData.date.isNotBlank()) {
                                datePickerState.updateDate(date = uiState.inputData.date)
                            }
                            datePickerState.show()
                        }
                    } else {
                        Modifier
                    },
                    date = uiState.inputData.date,
                )
            }

            item {
                RecordRefuelStation(
                    station = uiState.inputData.station.orEmpty(),
                    isInputMode = uiState.isInputMode,
                    onStationChanged = {
                        onInputDataChanged(uiState.inputData.copy(station = it))
                    },
                )
            }

            item {
                RecordRefuelPriceInfo(
                    info = uiState.inputData.priceInfo,
                    isInputMode = uiState.isInputMode,
                    onTotalPriceChanged = {
                        onInputDataChanged(
                            uiState.inputData.run {
                                copy(priceInfo = priceInfo.copy(totalPrice = it))
                            }
                        )
                    },
                    onPriceChanged = {
                        onInputDataChanged(
                            uiState.inputData.run {
                                copy(priceInfo = priceInfo.copy(price = it))
                            }
                        )
                    },
                    onAmountChanged = {
                        onInputDataChanged(
                            uiState.inputData.run {
                                copy(priceInfo = priceInfo.copy(amount = it))
                            }
                        )
                    },
                )
            }

            item {
                RecordRefuelMemo(
                    memo = uiState.inputData.memo.orEmpty(),
                    isInputMode = uiState.isInputMode,
                    onMemoChanged = {
                        onInputDataChanged(uiState.inputData.copy(memo = it))
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(124.dp))
            }
        }
    }

    LaunchedEffect(Unit) {
        when (uiState) {
            RecordRefuelUiState.EMPTY -> {
                coroutineScope.launch {
                    scrollState.scrollTo(0)
                }
            }
            else -> Unit
        }
    }
}

@Composable
fun RecordRefuelTitle(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TypoText(
            text = stringResource(id = R.string.record_refuel_main_title),
            typoStyle = TypoStyle.DISPLAY_SMALL_24
        )

        Spacer(modifier = Modifier.width(4.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_record_refuel_charge),
            contentDescription = null
        )
    }
}


@Composable
fun RecordRefuelInputDate(
    modifier: Modifier = Modifier,
    date: String,
) {
    InputTextBox(
        modifier = modifier,
        title = stringResource(id = R.string.record_refuel_input_date_title),
        hintText = stringResource(id = R.string.record_refuel_input_date_hint),
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
fun RecordRefuelStation(
    modifier: Modifier = Modifier,
    station: String = "",
    isInputMode: Boolean,
    onStationChanged: (String) -> Unit,
) {
    InputTextBox(
        modifier = modifier,
        title = stringResource(id = R.string.record_refuel_input_station_title),
        hintText = stringResource(id = R.string.record_refuel_input_station_hint),
        intPutText = station,
        inputTextFiledEnabled = isInputMode,
        onInputTextChange = onStationChanged
    )
}

@Composable
fun RecordRefuelPriceInfo(
    modifier: Modifier = Modifier,
    info: RecordRefuelUiState.InputData.PriceInfo,
    isInputMode: Boolean,
    onTotalPriceChanged: (String) -> Unit,
    onPriceChanged: (String) -> Unit,
    onAmountChanged: (String) -> Unit,
) {
    InputTextGroupBox(
        modifier = modifier,
        title = stringResource(id = R.string.record_refuel_input_info_title),
    ) {
        // 주유 금액
        InputTextBoxInGroup(
            title = stringResource(id = R.string.record_refuel_input_total_price_title),
            hintText = stringResource(id = R.string.record_refuel_input_total_price_hint),
            intPutText = info.totalPrice,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            inputTextFiledEnabled = isInputMode,
            importanceCount = 1,
            onInputTextChange = onTotalPriceChanged
        )
        // 주유 단가
        InputTextBoxInGroup(
            title = stringResource(id = R.string.record_refuel_input_price_title),
            hintText = stringResource(id = R.string.record_refuel_input_price_hint),
            intPutText = info.price,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            inputTextFiledEnabled = isInputMode,
            importanceCount = 1,
            onInputTextChange = onPriceChanged,
        )
        // 주유량
        InputTextBoxInGroup(
            title = stringResource(id = R.string.record_refuel_input_amount_title),
            hintText = stringResource(id = R.string.record_refuel_input_amount_hint),
            intPutText = info.amount,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            inputTextFiledEnabled = isInputMode,
            importanceCount = 1,
            onInputTextChange = onAmountChanged,
        )
    }
}

@Composable
fun RecordRefuelMemo(
    modifier: Modifier = Modifier,
    memo: String = "",
    isInputMode: Boolean,
    onMemoChanged: (String) -> Unit,
) {
    InputTextBox(
        title = stringResource(id = R.string.record_refuel_input_memo_title),
        hintText = stringResource(id = R.string.record_refuel_input_memo_hint),
        intPutText = memo,
        inputTextFiledEnabled = isInputMode,
        modifier = modifier,
        onInputTextChange = onMemoChanged
    )
}

@Preview
@Composable
fun RecordRefuelScreenPreview() {
    RecordRefuelScreen(
        uiState = RecordRefuelUiState.EMPTY,
        onInputDateChanged = {},
        onInputDataChanged = {},
        onClickedSave = {},
        onClickedBack = {}
    )
}