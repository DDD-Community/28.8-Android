package com.ddd.carssok.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ddd.carssok.core.designsystem.R
import com.ddd.carssok.core.designsystem.TypoStyle

@Composable
fun CarssokBottomSheetContent(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier,
        color = colorResource(id = R.color.primary_bg)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_drag_handle),
                modifier = modifier.padding(vertical = 16.dp),
                contentDescription = null
            )

            content()
        }
    }
}

@Composable
fun <T> CarssokBottomSheetListContent(
    items: List<T>,
    modifier: Modifier = Modifier,
    headerContent: (@Composable () -> Unit)? = null,
    itemSpaceSize: Float = 0f,
    itemContent: @Composable (T) -> Unit,
) {
    LazyColumn(
        modifier = modifier
    ) {
        headerContent?.let {
            item {
                it()
            }
        }

        itemsIndexed(items) { index, item ->
            itemContent(item)

            if(itemSpaceSize > 0 && index < items.size) {
                Spacer(modifier = modifier.size(Dp(itemSpaceSize)))
            }
        }
    }
}

@Preview
@Composable
fun CarssokBottomSheetContentPreview() {
    CarssokBottomSheetContent {
        CarssokBottomSheetListContent(
            items = listOf("first item", "second item"),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 21.dp),
            itemSpaceSize = 12f,
            headerContent = {
                TypoText(text = "Header Title", typoStyle = TypoStyle.HEADLINE_LARGE_20)
            }
        ) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.gray30))
            ) {
                TypoText(text = item, typoStyle = TypoStyle.HEADLINE_SMALL_16)
            }
        }
    }
}