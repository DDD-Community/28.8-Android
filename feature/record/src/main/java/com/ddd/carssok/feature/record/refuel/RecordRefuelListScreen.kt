package com.ddd.carssok.feature.record.refuel

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddd.carssok.core.designsystem.R
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.Appbar
import com.ddd.carssok.core.designsystem.component.CarssokIconButton
import com.ddd.carssok.core.designsystem.component.TypoText

@Composable
fun RecordRefuelListRoute(
    onClickedBack: () -> Unit,
) {
    RecordRefuelListScreen(
        onClickedBack = onClickedBack,
        onClickedAddButton = {},
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordRefuelListScreen(
    onClickedBack: () -> Unit,
    onClickedAddButton: () -> Unit,
) {
    Scaffold(
        topBar = {
            Appbar(
                titleRes = com.ddd.carssok.feature.record.R.string.record_refuel_list_app_bar_title,
                backButtonImageResource = R.drawable.ic_arrow_back_circle_32,
                onClickedBack = onClickedBack
            )
        },
        containerColor = colorResource(id = R.color.primary_bg),
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
                    month = 11,
                )
            }

            item {
                RecordRefuelMonthTotalPrice(
                    totalPrice = "631,200"
                )
            }

            item {
                RecordRefuelListUsageTitle(
                    modifier = Modifier.padding(top = 48.dp, bottom = 8.dp),
                    year = 2022,
                    month = 11,
                )
            }

            itemsIndexed(dummyList) { index, item ->
                RecordRefuelListDetailItem(
                    item = item,
                )
                
                if(index < dummyList.lastIndex) {
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
                painter = painterResource(id = com.ddd.carssok.feature.record.R.drawable.ic_record_refuel_charge),
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
            color = colorResource(id = R.color.tertiary_text),
        )
        TypoText(
            text = stringResource(id = com.ddd.carssok.feature.record.R.string.record_refuel_list_price),
            typoStyle = TypoStyle.DISPLAY_LARGE_32,
            color = colorResource(id = R.color.tertiary_text),
        )
    }
}

@Composable
fun RecordRefuelListUsageTitle(
    modifier: Modifier = Modifier,
    year: Int,
    month: Int,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(painter = painterResource(id = com.ddd.carssok.feature.record.R.drawable.ic_record_refuel_list_calendar), contentDescription = null)

        Spacer(modifier = Modifier.width(4.dp))

        TypoText(
            text = stringResource(id = com.ddd.carssok.feature.record.R.string.record_refuel_list_usage_title, year, month),
            typoStyle = TypoStyle.BODY_SMALL_12,
            color = colorResource(id = R.color.secondary_text)
        )
    }
}

@Composable
fun RecordRefuelListDetailItem(
    modifier: Modifier = Modifier,
    item: RecordRefuelListDetailItemUiState,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color = colorResource(id = R.color.secondary_bg))
            .padding(start = 16.dp, top = 24.dp, end = 12.dp, bottom = 24.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Column(
            modifier = Modifier.weight(168f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            TypoText(
                text = item.title,
                typoStyle = TypoStyle.BODY_SMALL_12,
                color = colorResource(id = R.color.primary_text)
            )
            TypoText(
                text = item.date,
                typoStyle = TypoStyle.BODY_SMALL_12,
                color = colorResource(id = R.color.secondary_text)
            )
        }
        
        Spacer(modifier = Modifier.width(9.dp))

        Row(
            modifier = Modifier.weight(82+12+9f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TypoText(
                modifier = Modifier.padding(horizontal = 9.dp),
                text = item.price,
                typoStyle = TypoStyle.HEADLINE_SMALL_16,
            )

            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_arrow_right_16),
                tint = colorResource(id = R.color.primary_text),
                contentDescription = null,
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RecordRefuelListPreview() {
    RecordRefuelListScreen(
        onClickedBack = {},
        onClickedAddButton = {},
    )
}