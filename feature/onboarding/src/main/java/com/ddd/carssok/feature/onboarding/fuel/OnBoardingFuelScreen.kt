package com.ddd.carssok.feature.onboarding.fuel

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.Appbar
import com.ddd.carssok.core.designsystem.component.CarssokButton
import com.ddd.carssok.core.designsystem.component.Chip
import com.ddd.carssok.core.designsystem.component.TypoText
import com.ddd.carssok.feature.onboarding.OnBoardingViewModel
import com.ddd.carssok.feature.onboarding.R

@Composable
fun OnBoardingFuelRoute(
    onClickedDone: () -> Unit,
    onBackPressed: () -> Unit,
    viewModel: OnBoardingFuelViewModel = hiltViewModel()
) {
    OnBoardingFuelScreen(
        onClickedDone = {
            viewModel.onClickedDone()
            onClickedDone()
        },
        onBackPressed = onBackPressed,
        onClickedChipFuel = viewModel::onClickedFilterFuel,
        viewModel = viewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingFuelScreen(
    onClickedDone: () -> Unit,
    onBackPressed: () -> Unit,
    onClickedChipFuel: (Boolean) -> Unit,
    viewModel: OnBoardingFuelViewModel
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            Appbar(
                onClickedBack = onBackPressed
            )
        },
        containerColor = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_bg),
        floatingActionButton = {
            CarssokButton(
                modifier = Modifier.size(width = 160.dp, height = 56.dp),
                titleRes = R.string.on_boarding_select_brand_next,
                isEnabled = true,
                onClicked = onClickedDone
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            OnBoardingFuelCarInfo(state.carName, state.carImageUrl)
            Divider(
                color = colorResource(com.ddd.carssok.core.designsystem.R.color.secondary_bg),
                thickness = 8.dp
            )
            OnBoardingFuelListHeader()
            OnBoardingFuelList(state.fuelList, onClickedChipFuel)
        }
    }

}

@Composable
fun OnBoardingFuelListHeader() {
    Box(modifier = Modifier.padding(top = 32.dp, start = 24.dp, end = 24.dp)) {
        TypoText(
            text = stringResource(id = R.string.on_boarding_fuel_select),
            typoStyle = TypoStyle.HEADLINE_X_SMALL_14
        )
    }
}

@Composable
fun OnBoardingFuelList(fuelList: List<FuelType>, onClickedChipFuel: (Boolean) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 12.dp
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(fuelList.count()) { index ->
                OnBoardingFuelCard(fuelList[index].koreanName, onClickedChipFuel)
            }
        }
    )
}

@Composable
fun OnBoardingFuelCard(name: String, onClickedChipFuel: (Boolean) -> Unit) {
    Chip(text = name, onClicked = onClickedChipFuel)
}

@Composable
fun OnBoardingFuelCarInfo(carName: String, carImageUrl: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 44.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(width = 270.dp, height = 112.dp),
            painter = painterResource(id = com.ddd.carssok.core.designsystem.R.drawable.ic_check_14),
            contentDescription = null,
            tint = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_text)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            textAlign = TextAlign.Center,
            fontSize = with(LocalDensity.current) { Dp(20f).toSp() },
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = colorResource(
                            com.ddd.carssok.core
                                .designsystem.R.color
                                .tertiary_text
                        )
                    )
                ) {
                    append(carName)
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_text)
                    )
                ) {
                    append("에\n")
                    append("맞는 연료만 선택하면 끝이에요.")
                }
            }
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun OnBoardingFuelScreenPreview() {
    OnBoardingFuelRoute(
        onClickedDone = {},
        onBackPressed = {}
    )
}