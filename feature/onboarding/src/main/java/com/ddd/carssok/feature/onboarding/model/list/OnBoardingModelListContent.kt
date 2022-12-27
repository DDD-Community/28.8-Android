package com.ddd.carssok.feature.onboarding.model.list

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddd.carssok.core.data.model.OnBoardingDetailModelEntity
import com.ddd.carssok.core.data.model.OnBoardingModelEntity
import com.ddd.carssok.core.designsystem.R
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.CarssokRadioButton
import com.ddd.carssok.core.designsystem.component.TypoText


@Composable
fun OnBoardingModelRow(
    model: OnBoardingModelEntity,
    isExpand: Boolean = false,
    onModelClicked: () -> Unit,
    onClickDetailModel: (OnBoardingDetailModelEntity?) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(bottom = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .clickable { onModelClicked() }
                .fillMaxWidth()
                .padding(top = 26.dp, bottom = 18.dp)
        ) {
            TypoText(
                text = model.title,
                typoStyle = TypoStyle.HEADLINE_X_SMALL_14,
                color = colorResource(id = R.color.primary_text)
            )
            Spacer(modifier = Modifier.size(6.dp))
            TypoText(
                text = "xxx~현재",
                typoStyle = TypoStyle.BODY_MEDIUM_14,
                color = colorResource(id = R.color.gray35),
                modifier = Modifier.weight(1f)
            )
            Image(
                painter = painterResource(id = if (isExpand)
                    R.drawable.ic_arrow_drop_up_18
                else
                    R.drawable.ic_arrow_drop_down_18
                ),
                contentDescription = null
            )
        }

        if(isExpand) {
            OnBoardingDetailModelGroup(
                items = model.detailModels,
                selectedItem = null,
                onSelect = onClickDetailModel
            )
        }
    }
}

@Composable
fun OnBoardingDetailModelGroup(
    items: List<OnBoardingDetailModelEntity>,
    selectedItem: OnBoardingDetailModelEntity? = null,
    onSelect: (OnBoardingDetailModelEntity?) -> Unit,
    modifier: Modifier = Modifier
) {
    var rememberSelectedItem by remember { mutableStateOf<OnBoardingDetailModelEntity?>(selectedItem) }

    // TODO LazyColumn in LazyColumn 이슈 해결하기
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(bottom = 16.dp)
    ) {
        items.forEachIndexed { index, detail ->
            val isChecked = rememberSelectedItem == detail

            OnBoardingDetailModelItem(
                item = detail,
                selected = isChecked,
                toggleSelectState = {
                    rememberSelectedItem = if(rememberSelectedItem == detail) {
                        null
                    } else {
                        detail
                    }
                    onSelect(rememberSelectedItem)
                }
            )
            
            if(index < items.lastIndex) {
                Spacer(modifier = modifier.size(8.dp))
            }
        }
    }
}

@Composable
fun OnBoardingDetailModelItem(
    item: OnBoardingDetailModelEntity,
    selected: Boolean,
    toggleSelectState: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(colorResource(id = R.color.secondary_bg)),
    ) {
        CarssokRadioButton(
            selected = selected,
            onClick = toggleSelectState
        )
        TypoText(
            text = item.year,
            typoStyle = if(selected)
                    TypoStyle.HEADLINE_X_SMALL_14
                else TypoStyle.BODY_MEDIUM_14,
            color = if(selected)
                    colorResource(id = R.color.tertiary_text)
                else
                    colorResource(id = R.color.secondary_text)
        )
    }
}


private val previewDummyItem = OnBoardingModelEntity(
    title = "제네시스 G90 (4세대)",
    detailModels = listOf(
        OnBoardingDetailModelEntity(
            year = "2022년형",
            title = "기본형",
            thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F4c1761%2FF66B79FB7D695898713EE125D1257421D607E2F2F774687741_2TPM",
        ),
        OnBoardingDetailModelEntity(
            year = "2022년형",
            title = "기본형 AWD",
            thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F4c1761%2FF66B79FB7D695898713EE125D1257421D607E2F2F774687741_2TPM",
        )
    )
)

@Preview
@Composable
fun OnBoardingModelRowPreview() {
    OnBoardingModelRow(
        model = previewDummyItem,
        isExpand = true,
        onModelClicked = {},
        onClickDetailModel = {}
    )
}


@Preview
@Composable
fun OnBoardingDetailModelGroupPreview() {
    OnBoardingDetailModelGroup(
        items = previewDummyItem.detailModels,
        selectedItem = null,
        onSelect = {}
    )
}

@Preview
@Composable
fun OnBoardingDetailModelItemPreview() {
    OnBoardingDetailModelItem(
        item = OnBoardingDetailModelEntity(
            year = "2022년형",
            title = "기본형",
            thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F4c1761%2FF66B79FB7D695898713EE125D1257421D607E2F2F774687741_2TPM",
        ),
        selected = true,
        toggleSelectState = {}
    )
}