package com.ddd.carssok.feature.record.drive

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.CarssokOutlinedButton
import com.ddd.carssok.core.designsystem.component.CustomAppbar
import com.ddd.carssok.core.designsystem.component.TypoText
import com.ddd.carssok.feature.record.R

@Composable
fun RecordDriveHistoryRoute(
    viewModel: RecordDriveHistoryViewModel = hiltViewModel(),
    onClickedBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val historyList by viewModel.historyList.collectAsState()

    RecordDriveHistoryScreen(
        uiState = uiState,
        historyList = historyList,
        onClickedBack = onClickedBack,
        onClickedEditButton = viewModel::changeEditMode,
        onClickedEditCancelButton = viewModel::changeNormalMode,
        onClickedDeleteItem = viewModel::deleteHistory,
        onClickedPreviousMonth = viewModel::previousMonth,
        onClickedNextMonth = viewModel::nextMonth,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordDriveHistoryScreen(
    uiState: RecordDriveHistoryViewModel.RecordDriveHistoryUiState,
    historyList: List<RecordDriveHistoryEntity>,
    onClickedBack: () -> Unit,
    onClickedEditButton: () -> Unit,
    onClickedEditCancelButton: () -> Unit,
    onClickedDeleteItem: (String) -> Unit,
    onClickedPreviousMonth: () -> Unit,
    onClickedNextMonth: () -> Unit,
) {
    Scaffold(
        topBar = {
            CustomAppbar(
                menuIcon = {
                    Image(painter = painterResource(id = com.ddd.carssok.core.designsystem.R.drawable.ic_close_36), contentDescription = null)
                },
                onClickedMenuItem = onClickedBack
            )
        },
        containerColor = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_bg),
        floatingActionButton = {
            when(uiState) {
                is RecordDriveHistoryViewModel.RecordDriveHistoryUiState.Edit -> {
                    CarssokOutlinedButton(
                        modifier = Modifier.widthIn(min = 136.dp),
                        titleRes = R.string.record_drive_history_edit_cancel_button,
                        radius = 99.dp,
                        leadingIcon = {
                            Icon(
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(end = 4.dp),
                                painter = painterResource(id = com.ddd.carssok.core.designsystem.R.drawable.ic_close_24),
                                tint = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_bg),
                                contentDescription = null
                            )
                        },
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = colorResource(id = R.color.record_drive_history_edit_cancel_button_background),
                            contentColor = colorResource(id = R.color.record_drive_history_edit_cancel_button_background),
                            disabledContainerColor = colorResource(id = R.color.record_drive_history_edit_cancel_button_background),
                            disabledContentColor = colorResource(id = R.color.record_drive_history_edit_cancel_button_background),
                        ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = colorResource(id = R.color.record_drive_history_edit_cancel_button_background),
                        ),
                        textColorRes = R.color.record_drive_history_edit_cancel_button_text,
                        isEnabled = true,
                        onClicked = onClickedEditCancelButton,
                    )
                }
                is RecordDriveHistoryViewModel.RecordDriveHistoryUiState.Normal -> {
                    CarssokOutlinedButton(
                        modifier = Modifier.widthIn(min = 136.dp),
                        titleRes = R.string.record_drive_history_edit_button,
                        radius = 99.dp,
                        leadingIcon = {
                            Icon(
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(end = 4.dp),
                                painter = painterResource(id = com.ddd.carssok.core.designsystem.R.drawable.ic_delete_18),
                                tint = colorResource(id = com.ddd.carssok.core.designsystem.R.color.secondary_text),
                                contentDescription = null
                            )
                        },
                        isEnabled = true,
                        onClicked = onClickedEditButton,
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 24.dp),
        ) {
            RecordDriveHistoryTitle(
                modifier = Modifier.padding(top = 8.dp),
                year = 2022,
            )

            RecordDriveMonthPicker(
                modifier = Modifier.padding(top = 32.dp),
                month = uiState.month,
                onClickedPrevious = onClickedPreviousMonth,
                onClickedNext = onClickedNextMonth,
            )

            TypoText(
                modifier = Modifier.padding(top = 12.dp),
                text = stringResource(id = R.string.record_drive_history_list_title),
                typoStyle = TypoStyle.BODY_SMALL_12,
                color = colorResource(id = com.ddd.carssok.core.designsystem.R.color.secondary_text),
            )

            RecordDriveHistoryList(
                modifier = Modifier.padding(top = 10.dp),
                isEditMode = uiState is RecordDriveHistoryViewModel.RecordDriveHistoryUiState.Edit,
                items = historyList,
                onClickedDeleteItem = onClickedDeleteItem
            )
        }
    }
}

@Composable
fun RecordDriveHistoryTitle(
    modifier: Modifier = Modifier,
    year: Int,
) {
    Column(
        modifier = modifier,
    ) {
        TypoText(
            text = stringResource(id = R.string.record_drive_history_main_title), 
            typoStyle = TypoStyle.DISPLAY_SMALL_24,
        )
        Spacer(modifier = Modifier.height(14.dp))
        TypoText(
            text = stringResource(id = R.string.record_drive_history_sub_title, year),
            typoStyle = TypoStyle.HEADLINE_X_SMALL_14,
        )
    }
}

@Composable
fun RecordDriveMonthPicker(
    modifier: Modifier = Modifier,
    month: Int,
    onClickedPrevious: () -> Unit,
    onClickedNext: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            modifier = Modifier
                .padding(end = 14.dp)
                .size(12.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.Transparent,
                contentColor = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_text),
                disabledContentColor = Color.Transparent,
                disabledContainerColor = colorResource(id = com.ddd.carssok.core.designsystem.R.color.disable_text),
            ),
            onClick = onClickedPrevious
        ) {
            Icon(
                painterResource(id = com.ddd.carssok.core.designsystem.R.drawable.ic_arrow_filled_left),
                contentDescription = null
            )
        }

        TypoText(
            text = stringResource(id = R.string.record_drive_history_month_title, month),
            typoStyle = TypoStyle.HEADLINE_MEDIUM_18,
            color = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_text),
        )

        IconButton(
            modifier = Modifier
                .padding(start = 14.dp)
                .size(12.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.Transparent,
                contentColor = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_text),
                disabledContentColor = Color.Transparent,
                disabledContainerColor = colorResource(id = com.ddd.carssok.core.designsystem.R.color.disable_text),
            ),
            onClick = onClickedNext
        ) {
            Icon(
                painterResource(id = com.ddd.carssok.core.designsystem.R.drawable.ic_arrow_filled_right),
                contentDescription = null
            )
        }
    }
}

@Composable
fun RecordDriveHistoryList(
    modifier: Modifier = Modifier,
    isEditMode: Boolean = false,
    items: List<RecordDriveHistoryEntity> = emptyList(),
    onClickedDeleteItem: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(items) { index, item ->
            RecordDriveHistoryListItem(
                item = item,
                isEditMode = isEditMode,
                onClickDelete = {
                    onClickedDeleteItem(item.id)
                },
            )
        }
    }
}

@Composable
fun RecordDriveHistoryListItem(
    modifier: Modifier = Modifier,
    item: RecordDriveHistoryEntity,
    isEditMode: Boolean = false,
    onClickDelete: (RecordDriveHistoryEntity) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // delete icon
        if(isEditMode) {
            Image(
                modifier = Modifier
                    .clickable {
                        onClickDelete(item)
                    },
                painter = painterResource(id = R.drawable.ic_record_drive_delete_20),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(14.dp))
        }

        // day + date
        Column(
            modifier = Modifier
                .size(60.dp)
                .clip(shape = RoundedCornerShape(14.dp))
                .background(color = colorResource(id = com.ddd.carssok.core.designsystem.R.color.tertiary_bg)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            TypoText(
                text = item.day,
                typoStyle = TypoStyle.BODY_SMALL_12,
                color = colorResource(id = com.ddd.carssok.core.designsystem.R.color.white),
            )
            Spacer(modifier = Modifier.height(2.dp))
            TypoText(
                text = item.date,
                typoStyle = TypoStyle.HEADLINE_MEDIUM_18,
                color = colorResource(id = com.ddd.carssok.core.designsystem.R.color.white),
            )
        }

        Column(
            modifier = Modifier.padding(start = 14.dp),
        ) {
            TypoText(
                text = item.title,
                typoStyle = TypoStyle.BODY_SMALL_12
            )
            Spacer(modifier = Modifier.height(6.dp))
            TypoText(
                text = item.mileage.toString(),
                typoStyle = TypoStyle.HEADLINE_MEDIUM_18,
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RecordDriveHistoryPreview() {
    RecordDriveHistoryScreen(
        uiState = RecordDriveHistoryViewModel.RecordDriveHistoryUiState.Normal(2022, 12),
        historyList = emptyList(),
        onClickedBack = {},
        onClickedEditButton = {},
        onClickedEditCancelButton = {},
        onClickedDeleteItem = {},
        onClickedPreviousMonth = {},
        onClickedNextMonth = {},
    )
}