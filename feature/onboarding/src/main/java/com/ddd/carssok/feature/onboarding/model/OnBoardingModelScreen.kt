package com.ddd.carssok.feature.onboarding.model

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddd.carssok.core.data.model.OnBoardingDetailModelEntity
import com.ddd.carssok.core.data.model.OnBoardingModelEntity
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.CarssokButton
import com.ddd.carssok.core.designsystem.component.InputTextBox
import com.ddd.carssok.core.designsystem.component.TypoText
import com.ddd.carssok.feature.onboarding.R
import kotlinx.coroutines.flow.StateFlow

@Composable
fun OnBoardingModelRoute(
    onDone: () -> Unit,
    onBackPressed: () -> Unit,
    viewModel: OnBoardingModelViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState
    
    OnBoardingModelScreen(
        uiState = uiState,
        search = viewModel::getModels,
        onModelCheckedChange = { selectedModel, isSelect ->

        },
        onButtonClicked = {
            onDone()
        }
    )
}

@Composable
fun OnBoardingModelScreen(
    uiState: StateFlow<OnBoardingModelUiState>,
    search: (String) -> Unit,
    onModelCheckedChange: (OnBoardingDetailModelEntity, Boolean) -> Unit,
    onButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isNextButtonEnable by remember { mutableStateOf(false) }

    Box() {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = modifier.padding(top = 28.dp, start = 24.dp, bottom = 20.dp, end = 24.dp)
            ) {
                TypoText(
                    text = "차량의 상세 모델도\n선택해주세요.",
                    typoStyle = TypoStyle.DISPLAY_SMALL_24,
                )
            }

            OnBoardingModelInputTextBox(
                onInputTextChange = search
            )

            OnBoardingModelContent(
                uiState = uiState.collectAsState(),
                onModelCheckedChange = { selectedModel, isChecked ->
                    isNextButtonEnable = isChecked
                    onModelCheckedChange(selectedModel, isChecked)
                },
                modifier = modifier
            )
        }

        CarssokButton(
            titleRes = R.string.button_title_next,
            isEnabled = isNextButtonEnable,
            onClicked = onButtonClicked,
            modifier = modifier.align(Alignment.BottomCenter)
        )
        Spacer(modifier = modifier.size(29.dp))
    }
}

@Composable
fun OnBoardingModelInputTextBox(
    onInputTextChange: (String) -> Unit,
) {
    var inputText by remember { mutableStateOf("") }

    InputTextBox(
        intPutText = inputText,
        title = "현대", // 브랜드
        hintText = "차량을 입력해주세요",
        onInputTextChange = {
            inputText = it
            onInputTextChange(it)
        }
    )
}

@Composable
fun ColumnScope.OnBoardingModelContent(
    uiState: State<OnBoardingModelUiState>,
    onModelCheckedChange: (OnBoardingDetailModelEntity, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .weight(1f)
        .padding(horizontal = 24.dp)
    ) {
        when(val state = uiState.value) {
            is OnBoardingModelUiState.Inputing -> {

            }
            is OnBoardingModelUiState.Loaded -> {
                var expandIndex by remember { mutableStateOf(-1) }

                LazyColumn(
                    modifier = modifier.fillMaxSize()
                ) {
                    itemsIndexed(state.modelList) { index, model ->
                        OnBoardingModelRow(
                            model = model,
                            isExpand = (expandIndex == index),
                            onModelClicked = {
                                expandIndex = if(expandIndex != index) {
                                    index
                                } else {
                                    -1
                                }
                            },
                            onModelCheckedChange = onModelCheckedChange,
                            modifier = modifier
                        )

                        if (index < state.modelList.lastIndex) {
                            Divider(
                                color = colorResource(id = com.ddd.carssok.core.designsystem.R.color.tertiary_bg),
                                thickness = 1.dp
                            )
                        }
                    }
                }
            }
            is OnBoardingModelUiState.Error -> Unit
        }
    }
}

@Composable
fun OnBoardingModelRow(
    model: OnBoardingModelEntity,
    isExpand: Boolean = false,
    onModelClicked: () -> Unit,
    onModelCheckedChange: (OnBoardingDetailModelEntity, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable { onModelClicked() }
                .padding(top = 26.dp, bottom = 22.dp)
        ) {
            TypoText(text = model.title, typoStyle = TypoStyle.HEADLINE_X_SMALL_14)
            TypoText(text = "xxx~현재", typoStyle = TypoStyle.BODY_MEDIUM_14, modifier = Modifier.weight(1f))
            Icon(painter = painterResource(
                id = if (isExpand)
                        com.google.android.material.R.drawable.mtrl_ic_arrow_drop_up
                    else
                        com.google.android.material.R.drawable.mtrl_ic_arrow_drop_down),
                contentDescription = null)
        }

        if(isExpand) {
            Column {
                model.detailModels.forEach { detail ->
                    OnBoardingDetailModelItem(
                        item = detail,
                        onCheckedChange = {
                            onModelCheckedChange(detail, it)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun OnBoardingDetailModelItem(
    item: OnBoardingDetailModelEntity,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var checked by remember { mutableStateOf(false) }

    Row(
        modifier = modifier.background(colorResource(id = com.ddd.carssok.core.designsystem.R.color.secondary_bg)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = {
                checked = it
                onCheckedChange(it)
            }
        )
        TypoText(
            text = item.year,
            typoStyle = if(checked)
                            TypoStyle.HEADLINE_X_SMALL_14
                        else TypoStyle.BODY_MEDIUM_14,
            color = if(checked)
                        colorResource(id = com.ddd.carssok.core.designsystem.R.color.tertiary_text)
                    else
                        colorResource(id = com.ddd.carssok.core.designsystem.R.color.secondary_text)
        )
    }
    
}

@Preview
@Composable
fun OnBoardingModelScreenPreview() {
    OnBoardingModelRoute(
        onDone = {},
        onBackPressed = {}
    )
}