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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.ddd.carssok.feature.record.R

@Composable
fun RecordRefuelRoute(
    viewModel: RecordRefuelViewModel = hiltViewModel(),
    onClickedBack: () -> Unit,
) {
    RecordRefuelScreen(
        onClickedSave = {},
        onClickedBack = onClickedBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordRefuelScreen(
    modifier: Modifier = Modifier,
    onClickedSave: (RecordRefuelInputData) -> Unit,
    onClickedBack: () -> Unit,
) {
    var rememberInputData by remember { mutableStateOf(RecordRefuelInputData.EMPTY) }

    var rememberSaveButtonEnabled by remember {
        mutableStateOf(
            rememberInputData.isNotBlank()
        )
    }

    LaunchedEffect(key1 = rememberInputData) {
        rememberSaveButtonEnabled = rememberInputData.isNotBlank()
    }

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
                isEnabled = rememberSaveButtonEnabled,
                modifier = modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 24.dp),
                onClicked = {
                    onClickedSave(rememberInputData)
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { padding ->

        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            item {
                RecordRefuelTitle(
                    modifier = modifier.padding(top = 20.dp, start = 24.dp, bottom = 28.dp, end = 24.dp)
                )
            }

            item {
                RecordRefuelInputDate(
                    modifier = modifier,
                    date = rememberInputData.date,
                    onDateChanged = {
                        rememberInputData = rememberInputData.copy(date = it)
                    },
                )
            }

            item {
                RecordRefuelStation(
                    modifier = modifier,
                    onStationChanged = {
                        rememberInputData = rememberInputData.copy(station = it)
                    },
                )
            }

            item {
                RecordRefuelInfo(
                    modifier = modifier,
                    totalPrice = rememberInputData.totalPrice,
                    price = rememberInputData.price,
                    amount = rememberInputData.amount,
                    onTotalPriceChanged = {
                        rememberInputData = rememberInputData.copy(totalPrice = it)
                    },
                    onPriceChanged = {
                        rememberInputData = rememberInputData.copy(price = it)
                    },
                    onAmountChanged = {
                        rememberInputData = rememberInputData.copy(amount = it)
                    }
                )
            }

            item {
                RecordRefuelMemo(
                    modifier = modifier,
                    onMemoChanged = {
                        rememberInputData = rememberInputData.copy(memo = it)
                    }
                )
            }

            item {
                Spacer(modifier = modifier.height(124.dp))
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
    onStationChanged: (String) -> Unit,
) {
    InputTextBox(
        modifier = modifier,
        title = stringResource(id = R.string.record_refuel_input_station_title),
        hintText = stringResource(id = R.string.record_refuel_input_station_hint),
        intPutText = "",
        inputTextFiledEnabled = true,
        onInputTextChange = onStationChanged
    )
}

@Composable
fun RecordRefuelInfo(
    modifier: Modifier = Modifier,
    totalPrice: String,
    price: String,
    amount: String,
    onTotalPriceChanged: (String) -> Unit,
    onPriceChanged: (String) -> Unit,
    onAmountChanged: (String) -> Unit,
) {
    InputTextGroupBox(
        modifier = modifier,
        title = stringResource(id = R.string.record_refuel_input_info_title),
        inputTextBoxes = listOf(
            {
                // 주유 금액
                InputTextBox(
                    title = stringResource(id = R.string.record_refuel_input_total_price_title),
                    hintText = stringResource(id = R.string.record_refuel_input_total_price_hint),
                    intPutText = totalPrice,
                    importanceCount = 1,
                    modifier = modifier,
                    onInputTextChange = onTotalPriceChanged
                )
            },
            {
                // 주유 단가
                InputTextBox(
                    title = stringResource(id = R.string.record_refuel_input_price_title),
                    hintText = stringResource(id = R.string.record_refuel_input_price_hint),
                    intPutText = price,
                    importanceCount = 1,
                    modifier = modifier,
                    onInputTextChange = onPriceChanged
                )
            },
            {
                // 주유량
                InputTextBox(
                    title = stringResource(id = R.string.record_refuel_input_amount_title),
                    hintText = stringResource(id = R.string.record_refuel_input_amount_hint),
                    intPutText = amount,
                    importanceCount = 1,
                    modifier = modifier,
                    onInputTextChange = onAmountChanged
                )
            },
        ),
    )
}

@Composable
fun RecordRefuelMemo(
    modifier: Modifier = Modifier,
    onMemoChanged: (String) -> Unit,
) {
    InputTextBox(
        title = stringResource(id = R.string.record_refuel_input_memo_title),
        hintText = stringResource(id = R.string.record_refuel_input_memo_hint),
        intPutText = "",
        modifier = modifier,
        onInputTextChange = onMemoChanged
    )
}

@Preview
@Composable
fun RecordRefuelScreenPreview() {
    RecordRefuelScreen(
        onClickedSave = {},
        onClickedBack = {}
    )
}