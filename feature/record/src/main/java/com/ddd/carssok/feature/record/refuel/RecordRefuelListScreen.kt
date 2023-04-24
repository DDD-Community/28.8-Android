package com.ddd.carssok.feature.record.refuel

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.Appbar
import com.ddd.carssok.core.designsystem.component.CarssokIconButton
import com.ddd.carssok.core.designsystem.component.TypoText
import com.ddd.carssok.feature.record.R

@Composable
fun RecordRefuelListRoute(
    viewModel: RecordRefuelListViewModel = hiltViewModel(),
    onClickedBack: () -> Unit,
    navigateToRecordRefuel: () -> Unit,
    navigateToRefuelDetail: (Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    RecordRefuelListScreen(
        uiState = uiState,
        onClickedBack = onClickedBack,
        onClickedAddButton = navigateToRecordRefuel,
        onClickedEditButton = viewModel::changeEditMode,
        onClickedEditCancelButton = viewModel::changeNormalMode,
        onClickedItem = navigateToRefuelDetail,
        onClickedDeleteItem = viewModel::deleteHistory
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordRefuelListScreen(
    uiState: RecordRefuelListUiState,
    onClickedBack: () -> Unit,
    onClickedAddButton: () -> Unit,
    onClickedEditButton: () -> Unit,
    onClickedEditCancelButton: () -> Unit,
    onClickedItem: (Int) -> Unit,
    onClickedDeleteItem: (Int) -> Unit,
) {
    BackHandler(enabled = uiState.isEditMode) {
        // 편집모드 취소
        onClickedEditCancelButton()
    }

    Scaffold(
        topBar = {
            Appbar(
                titleRes = com.ddd.carssok.feature.record.R.string.record_refuel_list_app_bar_title,
                backButtonImageResource = com.ddd.carssok.core.designsystem.R.drawable.ic_arrow_back_circle_32,
                onClickedBack = onClickedBack
            )
        },
        containerColor = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_bg),
        floatingActionButton = {
            CarssokIconButton(
                modifier = Modifier.height(52.dp),
                titleRes = com.ddd.carssok.feature.record.R.string.record_refuel_list_add_button_title,
                isEnabled = true,
                leadingIcon = {
                    Icon(
                        modifier = Modifier.padding(end = 4.dp),
                        painter = painterResource(id = com.ddd.carssok.feature.record.R.drawable.ic_record_edit),
                        contentDescription = null
                    )
                },
                onClicked = onClickedAddButton
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            item {
                RecordRefuelListTitle(
                    modifier = Modifier.padding(top = 28.dp, end = 48.dp),
                    month = uiState.selectedMonth,
                )
            }

            item {
                RecordRefuelMonthTotalPrice(
                    totalPrice = uiState.monthlyTotalPriceText
                )
            }

            item {
                RecordRefuelListUsageTitle(
                    modifier = Modifier.padding(top = 48.dp, bottom = 8.dp),
                    year = uiState.selectedYear,
                    month = uiState.selectedMonth,
                    isEditMode = uiState.mode is RecordRefuelListUiState.Mode.Edit,
                    onClickedEditButton = onClickedEditButton,
                    onClickedEditCancelButton = onClickedEditCancelButton,
                )
            }

            itemsIndexed(uiState.list) { index, item ->
                RecordRefuelListItem(
                    item = item,
                    isEditMode = uiState.mode is RecordRefuelListUiState.Mode.Edit,
                    onClickedItem = onClickedItem,
                    onClickDelete = onClickedDeleteItem
                )
                
                if(index < uiState.list.lastIndex) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

        }
    }
}

@Composable
fun RecordRefuelListTitle(
    modifier: Modifier = Modifier,
    month: Int,
) {
    Column(
        modifier = modifier
    ) {
        TypoText(
            text = stringResource(id = com.ddd.carssok.feature.record.R.string.record_refuel_list_main_title1, month),
            typoStyle = TypoStyle.DISPLAY_SMALL_24
        )
        Spacer(modifier = Modifier.size(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TypoText(
                text = stringResource(id = com.ddd.carssok.feature.record.R.string.record_refuel_list_main_title2),
                typoStyle = TypoStyle.DISPLAY_SMALL_24
            )
            Image(
                painter = painterResource(id = R.drawable.ic_record_refuel_charge),
                contentDescription = null
            )
        }
    }
}

@Composable
fun RecordRefuelMonthTotalPrice(
    modifier: Modifier = Modifier,
    totalPrice: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {
        TypoText(
            text = totalPrice,
            typoStyle = TypoStyle.DISPLAY_X_LARGE_44,
            color = colorResource(id = com.ddd.carssok.core.designsystem.R.color.tertiary_text),
        )
        TypoText(
            text = stringResource(id = com.ddd.carssok.feature.record.R.string.record_refuel_list_price),
            typoStyle = TypoStyle.DISPLAY_LARGE_32,
            color = colorResource(id = com.ddd.carssok.core.designsystem.R.color.tertiary_text),
        )
    }
}

@Composable
fun RecordRefuelListUsageTitle(
    modifier: Modifier = Modifier,
    year: Int,
    month: Int,
    isEditMode: Boolean,
    onClickedEditButton: () -> Unit,
    onClickedEditCancelButton: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(painter = painterResource(id = R.drawable.ic_record_refuel_list_calendar), contentDescription = null)

        Spacer(modifier = Modifier.width(4.dp))

        TypoText(
            text = stringResource(id = com.ddd.carssok.feature.record.R.string.record_refuel_list_usage_title, year, month),
            typoStyle = TypoStyle.BODY_SMALL_12,
            color = colorResource(id = com.ddd.carssok.core.designsystem.R.color.secondary_text)
        )

        Spacer(modifier = Modifier.weight(1f))

        TypoText(
            modifier = Modifier.clickable {
                if (isEditMode) {
                    onClickedEditCancelButton()
                } else {
                    onClickedEditButton()
                }
            },
            text = stringResource(id = com.ddd.carssok.feature.record.R.string.record_refuel_list_edit_button_title, year, month),
            typoStyle = TypoStyle.BODY_MEDIUM_14,
            color = colorResource(id = com.ddd.carssok.core.designsystem.R.color.gray40),
        )
    }
}

@Composable
fun RecordRefuelListItem(
    modifier: Modifier = Modifier,
    item: RecordRefuelListUiState.Item,
    isEditMode: Boolean,
    onClickedItem: (Int) -> Unit,
    onClickDelete: (Int) -> Unit,
) {
    Row(
        modifier = modifier.clickable {
            if (!isEditMode) {
              onClickedItem(item.id)
            }
        },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (isEditMode) {
            Image(
                modifier = Modifier
                    .clickable {
                        onClickDelete(item.id)
                    },
                painter = painterResource(id = com.ddd.carssok.feature.record.R.drawable.ic_record_delete_20),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(14.dp))
        }

        val itemContentModifier = Modifier

        Row(
            modifier = itemContentModifier
                .clip(RoundedCornerShape(12.dp))
                .background(color = colorResource(id = com.ddd.carssok.core.designsystem.R.color.secondary_bg))
                .padding(start = 16.dp, top = 24.dp, end = 12.dp, bottom = 24.dp),
            verticalAlignment = Alignment.Top,
        ) {
            Column(
                modifier = itemContentModifier.weight(168f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                TypoText(
                    text = item.title,
                    typoStyle = TypoStyle.BODY_SMALL_12,
                    color = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_text)
                )

                TypoText(
                    text = stringResource(
                        id = item.dateResId,
                        item.month,
                        item.day,
                        stringResource(id = item.weekDayResId)
                    ),
                    typoStyle = TypoStyle.BODY_SMALL_12,
                    color = colorResource(id = com.ddd.carssok.core.designsystem.R.color.secondary_text)
                )
            }

            Spacer(modifier = itemContentModifier.width(9.dp))

            Row(
                modifier = itemContentModifier.weight(103f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TypoText(
                    modifier = itemContentModifier,
                    text = stringResource(id = com.ddd.carssok.feature.record.R.string.record_refuel_list_item_price, item.priceText),
                    typoStyle = TypoStyle.HEADLINE_SMALL_16,
                )

                if (!isEditMode) {
                    Icon(
                        modifier = itemContentModifier.size(16.dp),
                        painter = painterResource(id = com.ddd.carssok.core.designsystem.R.drawable.ic_arrow_right_16),
                        tint = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_text),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RecordRefuelListPreview() {
    RecordRefuelListScreen(
        uiState = RecordRefuelListUiState.EMPTY,
        onClickedBack = {},
        onClickedAddButton = {},
        onClickedEditButton = {},
        onClickedEditCancelButton = {},
        onClickedItem = {},
        onClickedDeleteItem = {},
    )
}