package com.ddd.carssok.feature.onboarding.brand

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.CarssockCheckbox
import com.ddd.carssok.core.designsystem.component.CarssokButton
import com.ddd.carssok.core.designsystem.component.TypoText
import com.ddd.carssok.core.model.CarBrand
import com.ddd.carssok.feature.onboarding.R

@Composable
fun OnBoardingBrandRoute(
    onDone: () -> Unit,
    onBackPressed: () -> Unit,
    viewModel: OnBoardingBrandViewModel = hiltViewModel()
) {
    OnBoardingBrandScreen(
        viewModel = viewModel,
        onChecked = { id, isChecked ->
            viewModel.onCheckedBrandCard(id, isChecked)
        },
        onClickedNextButton = onDone
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingBrandScreen(
    viewModel: OnBoardingBrandViewModel,
    onChecked: (Long, Boolean) -> Unit,
    onClickedNextButton:() -> Unit,
) {
    val state by viewModel.uiState.collectAsState()
    Scaffold(
        containerColor = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_bg),
        floatingActionButton = {
            CarssokButton(
                modifier = Modifier.size(width = 160.dp, height = 56.dp),
                titleRes = R.string.on_boarding_select_brand_next,
                isEnabled = state.nextButtonEnabled,
                onClicked = onClickedNextButton
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(24.dp)
        ) {
            item("temp header") {
                Spacer(modifier = Modifier.height(24.dp))
                Header()
            }
            item("temp koreBrand title") {
                CountryBrandCarTitle(R.string.on_boarding_select_brand_korea)
            }
            items(items = state.brandItems.chunked(3)) { items ->
                CountryBrandCar(items, onChecked = onChecked)
                Spacer(modifier = Modifier.height(10.dp))
            }

            item("temp foreign title") {
                Spacer(modifier = Modifier.height(44.dp))
                CountryBrandCarTitle(R.string.on_boarding_select_brand_foreign)
            }

            items(state.brandItems.chunked(3)) { items ->
                CountryBrandCar(items, onChecked = onChecked)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun Header() {
    TypoText(
        text = stringResource(id = R.string.on_boarding_select_brand_title),
        typoStyle = TypoStyle.DISPLAY_SMALL_24
    )
    Spacer(modifier = Modifier.height(16.dp))
    TypoText(
        text = stringResource(id = R.string.on_boarding_select_brand_subtitle),
        typoStyle = TypoStyle.HEADLINE_SMALL_16,
        color = colorResource(id = com.ddd.carssok.core.designsystem.R.color.secondary_text)
    )
    Spacer(modifier = Modifier.height(44.dp))
}

@Composable
fun CountryBrandCarTitle(titleRes: Int) {
    TypoText(
        text = stringResource(id = titleRes),
        typoStyle = TypoStyle.HEADLINE_SMALL_16,
        color = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_text)
    )
    Spacer(modifier = Modifier.height(14.dp))
}

@Composable
fun CountryBrandCar(items: List<CarBrand>, onChecked: (Long, Boolean) -> Unit) {
    Row {
        for ((index, item) in items.withIndex()) {
            Box(modifier = Modifier.fillMaxWidth(1f / (3 - index))) {
                CountryBrandCarItem(item.name, item.isChecked, onChecked = { onChecked.invoke(item.id, it) })
            }
        }
    }
}

@Composable
fun CountryBrandCarItem(
    name: String,
    isChecked: Boolean,
    onChecked: (Boolean) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.height(124.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = com.ddd.carssok.core.designsystem.R.color.secondary_bg)
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                CarssockCheckbox(isChecked, onChangedValue = {
                    onChecked(it)
                })
                Spacer(modifier = Modifier.size(8.dp))
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Icon(
                    modifier = Modifier.size(52.dp),
                    painter = painterResource(id = com.ddd.carssok.core.designsystem.R.drawable.ic_check_14),
                    contentDescription = null,
                    tint = colorResource(com.ddd.carssok.core.designsystem.R.color.red0)
                )
                Spacer(modifier = Modifier.height(2.dp))
                TypoText(
                    text = name,
                    typoStyle = TypoStyle.HEADLINE_X_SMALL_14,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun OnBoardingBrandScreenPreview() {
    OnBoardingBrandRoute(
        onDone = {},
        onBackPressed = {}
    )
}