package com.ddd.carssok.core.designsystem.component.input

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddd.carssok.core.designsystem.R
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.TypoText
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun InputImageBox(
    modifier: Modifier = Modifier,
    title: String,
    cardTitle: String,
    titleHint: String? = null,
    @DrawableRes cardIconRes: Int,
    saveList: List<Any> = emptyList(),
    onClickedAdd: () -> Unit,
    onClickedRemove: (Int) -> Unit
) {
    var rememberSaveList by remember { mutableStateOf(saveList) }
    Column(
        modifier = modifier
            .padding(vertical = 10.dp, horizontal = 24.dp)
    ) {
        Row {
            TypoText(
                text = title,
                typoStyle = TypoStyle.HEADLINE_X_SMALL_14
            )
            if (titleHint != null) {
                TypoText(
                    modifier = Modifier.padding(start = 1.dp),
                    color = colorResource(id = R.color.disable_text),
                    text = titleHint,
                    typoStyle = TypoStyle.HEADLINE_X_SMALL_14
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(0.dp, 234.dp),
            columns = GridCells.Fixed(3),
            userScrollEnabled = false,
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            content = {
                items(rememberSaveList.count()) { index ->
                    SaveCard(index) {
                        rememberSaveList = rememberSaveList.toMutableList().apply {
                            removeAt(index)
                        }
                        onClickedRemove.invoke(it)
                    }
                }
                item("") {
                    DefaultCard(cardTitle, cardIconRes, onClickedAdd)
                }
            }
        )
    }
}

@Composable
fun SaveCard(index: Int, onClickedRemove: (Int) -> Unit) {
    Box {
        GlideImage(
            modifier = Modifier
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp)),
            imageModel = { "https://picsum.photos/200/300" },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.5.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Icon(
                modifier = Modifier.clickable { onClickedRemove.invoke(index) },
                painter = painterResource(id = R.drawable.ic_close_24),
                contentDescription = null,
                tint = colorResource(id = R.color.primary_bg)
            )
        }
    }
}

@Composable
fun DefaultCard(cardTitle: String, @DrawableRes cardIconRes: Int, onClickedAdd: () -> Unit) {
    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClickedAdd.invoke() },
        border = BorderStroke(1.dp, colorResource(id = R.color.button_disabled)),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.secondary_bg)
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = cardIconRes),
                contentDescription = null,
                tint = colorResource(id = R.color.primary_text)
            )
            Spacer(modifier = Modifier.height(4.dp))
            TypoText(
                text = cardTitle,
                typoStyle = TypoStyle.BODY_SMALL_12,
                color = colorResource(id = R.color.primary_text)
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InputImageBoxPreview() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            InputImageBox(
                title = "사고 사진",
                titleHint = "(최대 5장)",
                cardTitle = "+추가하기",
                cardIconRes = R.drawable.ic_calendar_24,
                saveList = listOf(1, 2, 3, 4),
                onClickedAdd = {},
                onClickedRemove = {}
            )
        }
    }
}