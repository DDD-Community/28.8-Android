package com.ddd.carssok.feature.record.refuel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.Appbar
import com.ddd.carssok.core.designsystem.component.CarssokButton
import com.ddd.carssok.core.designsystem.component.TypoText
import com.ddd.carssok.core.designsystem.component.input.InputTextBox
import com.ddd.carssok.core.designsystem.component.input.InputTextGroupBox
import com.ddd.carssok.core.designsystem.component.input.InputTextBoxInGroup
import com.ddd.carssok.feature.record.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun RecordRefuelRoute(
    viewModel: RecordRefuelViewModel = hiltViewModel(),
    onClickedBack: () -> Unit,
) {
    RecordRefuelScreen(
        state = viewModel.uiState,
        onInputDataChanged = viewModel::updateInputData,
        onClickedSave = {},
        onClickedBack = onClickedBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordRefuelScreen(
    state: StateFlow<RecordRefuelUiState>,
    onInputDataChanged: (RecordRefuelInputData) -> Unit,
    onClickedSave: () -> Unit,
    onClickedBack: () -> Unit,
) {
    val uiState by state.collectAsState()

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
                titleRes = R.string.record_refuel_save_button_title,
                isEnabled = uiState.isSaveButtonEnable,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 24.dp),
                onClicked = onClickedSave
            )
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
                    date = uiState.inputData.date,
                    onDateChanged = {
                        onInputDataChanged(uiState.inputData.copy(date = it))
                    },
                )
            }

            item {
                RecordRefuelStation(
                    station = uiState.inputData.station.orEmpty(),
                    onStationChanged = {
                        onInputDataChanged(uiState.inputData.copy(station = it))
                    },
                )
            }

            item {
                RecordRefuelInfo(
                    info = uiState.inputData.priceInfo,
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
        Image(
            painter = painterResource(id = com.ddd.carssok.core.designsystem.R.drawable.ic_record_refuel),
            contentDescription = null
        )
    }
}


@Composable
fun RecordRefuelInputDate(
    modifier: Modifier = Modifier,
    date: String,
    onDateChanged: (String) -> Unit,
) {
    InputTextBox(
        modifier = modifier,
        title = stringResource(id = R.string.record_refuel_input_date_title),
        hintText = stringResource(id = R.string.record_refuel_input_date_hint),
        intPutText = date,
        inputTextFiledEnabled = true,
        importanceCount = 1,
        leadingIcon = {
            Icon(
                painter = painterResource(id = com.ddd.carssok.core.designsystem.R.drawable.ic_calendar_24),
                tint = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_text),
                contentDescription = null
            )
        },
        onInputTextChange = onDateChanged
    )
}

@Composable
fun RecordRefuelStation(
    modifier: Modifier = Modifier,
    station: String = "",
    onStationChanged: (String) -> Unit,
) {
    InputTextBox(
        modifier = modifier,
        title = stringResource(id = R.string.record_refuel_input_station_title),
        hintText = stringResource(id = R.string.record_refuel_input_station_hint),
        intPutText = station,
        inputTextFiledEnabled = true,
        onInputTextChange = onStationChanged
    )
}

@Composable
fun RecordRefuelInfo(
    modifier: Modifier = Modifier,
    info: RecordRefuelPriceInfo,
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
            importanceCount = 1,
            onInputTextChange = onTotalPriceChanged,
        )
        // 주유 단가
        InputTextBoxInGroup(
            title = stringResource(id = R.string.record_refuel_input_price_title),
            hintText = stringResource(id = R.string.record_refuel_input_price_hint),
            intPutText = info.price,
            importanceCount = 1,
            onInputTextChange = onPriceChanged,
        )
        // 주유량
        InputTextBoxInGroup(
            title = stringResource(id = R.string.record_refuel_input_amount_title),
            hintText = stringResource(id = R.string.record_refuel_input_amount_hint),
            intPutText = info.amount,
            importanceCount = 1,
            onInputTextChange = onAmountChanged,
        )
    }
}

@Composable
fun RecordRefuelMemo(
    modifier: Modifier = Modifier,
    memo: String = "",
    onMemoChanged: (String) -> Unit,
) {
    InputTextBox(
        title = stringResource(id = R.string.record_refuel_input_memo_title),
        hintText = stringResource(id = R.string.record_refuel_input_memo_hint),
        intPutText = memo,
        modifier = modifier,
        onInputTextChange = onMemoChanged
    )
}

@Preview
@Composable
fun RecordRefuelScreenPreview() {
    RecordRefuelScreen(
        state = MutableStateFlow(RecordRefuelUiState()),
        onInputDataChanged = {},
        onClickedSave = {},
        onClickedBack = {}
    )
}