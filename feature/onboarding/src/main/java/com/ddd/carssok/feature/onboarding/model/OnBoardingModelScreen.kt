package com.ddd.carssok.feature.onboarding.model

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddd.carssok.core.data.model.OnBoardingDetailModelEntity
import com.ddd.carssok.core.data.model.OnBoardingModelEntity
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.*
import com.ddd.carssok.feature.onboarding.R
import com.ddd.carssok.feature.onboarding.model.list.OnBoardingModelRow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun OnBoardingModelRoute(
    onDone: () -> Unit,
    onBackPressed: () -> Unit,
    viewModel: OnBoardingModelViewModel = hiltViewModel()
) {
    OnBoardingModelScreen(
        uiState = viewModel.uiState,
        search = viewModel::search,
        onClickDetailModel = viewModel::onModelSelected,
        onClickedNext = {
            onDone()
        },
        onBackPressed = onBackPressed
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingModelScreen(
    uiState: StateFlow<OnBoardingModelUiState>,
    search: (String) -> Unit,
    onClickDetailModel: (OnBoardingDetailModelEntity?) -> Unit,
    onClickedNext: () -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by uiState.collectAsState()

    Scaffold(
        topBar = {
            Appbar(
                titleRes = R.string.on_boarding_select_model_top_bar_title,
                onClickedBack = onBackPressed
            )
        },
        containerColor = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_bg),
        floatingActionButton = {
            OnBoardingModelNextButton(
                state = state,
                onClickedNext = onClickedNext
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Box(
                modifier = modifier.padding(vertical = 20.dp, horizontal = 24.dp)
            ) {
                TypoText(
                    text = stringResource(id = R.string.on_boarding_select_model_title),
                    typoStyle = TypoStyle.DISPLAY_SMALL_24,
                )
            }

            OnBoardingModelInputTextBox(
                onInputTextChange = search
            )

            OnBoardingModelContent(
                state = state,
                onClickDetailModel = onClickDetailModel,
                modifier = modifier
            )
        }
    }
}

@Composable
fun OnBoardingModelNextButton(
    state: OnBoardingModelUiState,
    onClickedNext: () -> Unit,
) {
    CarssokButton(
        titleRes = R.string.on_boarding_select_model_next,
        isEnabled = when(state) {
            is OnBoardingModelUiState.Inputing,
            is OnBoardingModelUiState.Error -> false
            is OnBoardingModelUiState.Loaded -> state.isNextButtonEnable
        },
        onClicked = onClickedNext,
        modifier = Modifier.size(width = 160.dp, height = 56.dp),
    )
}

@Composable
fun OnBoardingModelInputTextBox(
    onInputTextChange: (String) -> Unit,
) {
    var inputText by remember { mutableStateOf("") }

    InputTextBox(
        intPutText = inputText,
        title = "현대", // 브랜드
        hintText = stringResource(id = R.string.on_boarding_select_model_input_hint),
        onInputTextChange = {
            inputText = it
            onInputTextChange(it)
        }
    )
}

@Composable
fun ColumnScope.OnBoardingModelContent(
    state: OnBoardingModelUiState,
    onClickDetailModel: (OnBoardingDetailModelEntity?) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .weight(1f)
        .padding(horizontal = 24.dp)
    ) {
        when(state) {
            is OnBoardingModelUiState.Inputing -> {
                // TODO 실시간 검색
            }
            is OnBoardingModelUiState.Loaded -> {
                OnBoardingModelListContent(
                    modelList = state.modelList,
                    onClickDetailModel = onClickDetailModel
                )
            }
            is OnBoardingModelUiState.Error -> Unit
        }
    }
}

@Composable
fun OnBoardingModelListContent(
    modelList: List<OnBoardingModelEntity>,
    onClickDetailModel: (OnBoardingDetailModelEntity?) -> Unit,
    modifier: Modifier = Modifier
) {
    var expandIndex by remember { mutableStateOf(-1) }

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        itemsIndexed(modelList) { index, model ->
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
                onClickDetailModel = onClickDetailModel,
                modifier = modifier
            )

            if (index < modelList.lastIndex) {
                Divider(
                    color = colorResource(id = com.ddd.carssok.core.designsystem.R.color.divider_tertiary_),
                    thickness = 1.dp
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun OnBoardingModelScreenPreview() {
    OnBoardingModelRoute(
        onDone = {},
        onBackPressed = {}
    )
}