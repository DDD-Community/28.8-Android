package com.ddd.carssok.feature.record

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.CarssokBottomSheetContent
import com.ddd.carssok.core.designsystem.component.CarssokBottomSheetListContent
import com.ddd.carssok.core.designsystem.component.TypoText
import com.ddd.carssok.feature.record.navi.RecordNavItem
import com.ddd.carssok.feature.record.navi.RecordNavItems

@Composable
fun RecordNavigationBottomSheetContent(
    modifier: Modifier = Modifier,
    onItemClicked: (RecordNavItem) -> Unit,
) {
    CarssokBottomSheetContent {
        CarssokBottomSheetListContent(
            items = RecordNavItems.BottomSheetItems,
            headerContent = {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, start = 28.dp, bottom = 28.dp)
                ) {
                    TypoText(text = stringResource(id = R.string.record_title), typoStyle = TypoStyle.HEADLINE_LARGE_20)
                    Spacer(modifier = modifier.size(12.dp))
                    TypoText(
                        text = stringResource(id = R.string.record_sub_title),
                        typoStyle = TypoStyle.HEADLINE_SMALL_16,
                        color = colorResource(
                            id = com.ddd.carssok.core.designsystem.R.color.secondary_text
                        )
                    )
                }

            },
            itemSpaceSize = 12f,
            itemContent = { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClicked(item)
                        }
                        .height(52.dp)
                        .padding(start = 28.dp, end = 34.5.dp)
                ) {
                    Image(painter = painterResource(id = item.icon), contentDescription = null)
                    Spacer(modifier = modifier.size(12.dp))
                    TypoText(
                        text = stringResource(id = item.title),
                        typoStyle = TypoStyle.HEADLINE_SMALL_16,
                        modifier = modifier.weight(1f)
                    )
                    Icon(
                        painter = painterResource(id = com.ddd.carssok.core.designsystem.R.drawable.ic_arrow_right_12),
                        tint = colorResource(id = com.ddd.carssok.core.designsystem.R.color.divider_primary),
                        contentDescription = null
                    )
                }
            }
        )
    }
}