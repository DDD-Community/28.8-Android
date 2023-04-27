package com.ddd.carssok.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.CarssokOutlinedButton
import com.ddd.carssok.core.designsystem.component.TypoText
import com.ddd.carssok.core.util.StringUtils.toNumberString
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateTo: (String) -> Unit,
    navigateToOnBoarding: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        uiState = uiState,
        event = viewModel.event,
        navigateTo = navigateTo,
        navigateToOnBoarding = navigateToOnBoarding,
        navigateToHistoryList = {},
        navigateToMaintenanceTotal = {},
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    event: SharedFlow<HomeViewModel.Event>,
    navigateTo: (route: String) -> Unit,
    navigateToOnBoarding: () -> Unit,
    navigateToHistoryList: () -> Unit,
    navigateToMaintenanceTotal: () -> Unit,
) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController, isSystemInDarkTheme) {
        systemUiController.setStatusBarColor(
            color = if(isSystemInDarkTheme) HomeColors.gradientTopDark else HomeColors.gradientTopLight,
            darkIcons = !isSystemInDarkTheme
        )
        onDispose {
            systemUiController.setStatusBarColor(
                color = if(isSystemInDarkTheme) HomeColors.statusBarColorDark else HomeColors.statusBarColorLight,
                darkIcons = !isSystemInDarkTheme
            )
        }
    }

    LaunchedEffect(Unit) {
        event.collectLatest {
            when (it) {
                is HomeViewModel.Event.CheckedCarssokUser -> {
                    if (it.isCarssokUser.not()) {
                        navigateToOnBoarding()
                    }
                }
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(33.dp)
    ) {
        item {
            HomeMyCar(
                modifier = Modifier,
                item = uiState.car,
                totalDriveDistance = uiState.totalDriveDistance,
            )
        }

        items(uiState.widgets) {
            when (it) {
                is HomeUiState.Widget.History -> {
                    HomeHistoryWidget(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        items = it.list,
                        widgetTitle = it.widgetTitle,
                        widgetSubTitle = it.widgetSubTitle,
                        navigateToHistoryList = navigateToHistoryList,
                        onClickedItem = navigateTo
                    )
                }
                is HomeUiState.Widget.Maintenance -> {
                    HomeMaintenanceWidget(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        items = it.list,
                        widgetTitle = it.widgetTitle,
                        navigateToMaintenanceTotal = navigateToMaintenanceTotal,
                        onClickedItem = navigateTo
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.size(40.dp))
        }
    }
}

@Composable
fun HomeMyCar(
    modifier: Modifier = Modifier,
    item: HomeUiState.Car,
    totalDriveDistance: Int,
) {
    Box(modifier = modifier.height(397.dp)) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .height(360.dp)
                .background(
                    brush = Brush.verticalGradient(
                        HomeColors.getGradientColors(isSystemInDarkTheme())
                    ),
                )
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 40.dp),
        ) {
            TypoText(text = item.title, typoStyle = TypoStyle.HEADLINE_LARGE_20)

            TypoText(text = "${item.refuel} | ${item.model}", typoStyle = TypoStyle.HEADLINE_X_SMALL_14)

            GlideImage(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                imageModel = { "https://blog.kakaocdn.net/dn/btevEs/btrHFRtVKVO/4SLnouYLcnq5Ei5ocxSYL1/img.webp" },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.Center
                )
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 24.dp)
                .background(
                    brush = Brush.verticalGradient(HomeColors.totalDistanceGradientColors),
                    shape = RoundedCornerShape(24.dp),
                    alpha = 1.0f
                )
                .padding(horizontal = 18.dp)
                .fillMaxWidth()
                .height(74.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TypoText(
                text = "누적 주행 기록",
                typoStyle = TypoStyle.HEADLINE_MEDIUM_18,
                colorResource = com.ddd.carssok.core.designsystem.R.color.white,
            )
            TypoText(
                text = "${totalDriveDistance.toNumberString()} km",
                typoStyle = TypoStyle.HEADLINE_MEDIUM_18,
                colorResource = com.ddd.carssok.core.designsystem.R.color.white,
            )
        }
    }
}

@Composable
fun HomeWidgetContent(
    modifier: Modifier = Modifier,
    title: @Composable ColumnScope.() -> Unit,
    button: @Composable ColumnScope.() -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        title()
        
        Spacer(modifier = Modifier.size(12.dp))

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(28.dp))
                .background(colorResource(id = com.ddd.carssok.core.designsystem.R.color.secondary_bg))
                .padding(top = 32.dp, bottom = 24.dp, start = 18.dp, end = 18.dp),
        ) {
            content()

            Spacer(modifier = Modifier.size(8.dp))

            button()
        }
    }
}

@Composable
fun HomeHistoryWidget(
    modifier: Modifier = Modifier,
    widgetTitle: String,
    widgetSubTitle: String,
    items: List<HomeUiState.Widget.Item>,
    navigateToHistoryList: () -> Unit,
    onClickedItem: (String) -> Unit,
) {
    HomeWidgetContent(
        modifier = modifier,
        title = {
            Row {
                TypoText(
                    text = widgetTitle,
                    typoStyle = TypoStyle.HEADLINE_MEDIUM_18,
                )
                Image(painter = painterResource(id = R.drawable.ic_home_history_timer), contentDescription = null)
            }
            
            TypoText(
                text = widgetSubTitle,
                typoStyle = TypoStyle.BODY_SMALL_12,
            )
        },
        button = {
            CarssokOutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                titleRes = R.string.home_navigate_to_list_button,
                isEnabled = true,
                onClicked = navigateToHistoryList
            )
        },
    ) {
        items.forEach {
            HomeHistoryWidgetItem(
                item = it,
                onClicked = onClickedItem
            )

            Spacer(modifier = Modifier.size(24.dp))
        }
    }
}

@Composable
fun HomeHistoryWidgetItem(
    modifier: Modifier = Modifier,
    item: HomeUiState.Widget.Item,
    onClicked: (String) -> Unit,
) {
    Row(
        modifier = modifier.clickable { onClicked(item.category.route) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TypoText(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(colorResource(id = (item.category as HomeUiState.Widget.Category.Record).color))
                .size(44.dp)
                .wrapContentHeight(align = Alignment.CenterVertically),
            text = item.category.name,
            typoStyle = TypoStyle.BODY_SMALL_12,
            textAlign = TextAlign.Center,
            colorResource = com.ddd.carssok.core.designsystem.R.color.white,
        )
        Spacer(modifier = Modifier.size(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            TypoText(
                text = item.title,
                typoStyle = TypoStyle.HEADLINE_X_SMALL_14,
                colorResource = com.ddd.carssok.core.designsystem.R.color.primary_text,
            )
            TypoText(
                text = item.subText,
                typoStyle = TypoStyle.BODY_SMALL_12,
                colorResource = com.ddd.carssok.core.designsystem.R.color.secondary_text,
            )
        }
        Image(painter = painterResource(id = com.ddd.carssok.core.designsystem.R.drawable.ic_arrow_circel_right_28), contentDescription = null)
    }
}

@Composable
fun HomeMaintenanceWidget(
    modifier: Modifier = Modifier,
    widgetTitle: String,
    items: List<HomeUiState.Widget.Item>,
    navigateToMaintenanceTotal: () -> Unit,
    onClickedItem: (String) -> Unit,
) {
    HomeWidgetContent(
        modifier = modifier,
        title = {
            Row {
                TypoText(
                    text = widgetTitle,
                    typoStyle = TypoStyle.HEADLINE_MEDIUM_18,
                )
                Image(painter = painterResource(id = R.drawable.ic_home_maintenance), contentDescription = null)
            }
        },
        button = {
            CarssokOutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                titleRes = R.string.home_navigate_to_total_button,
                isEnabled = true,
                onClicked = navigateToMaintenanceTotal
            )
        }
    ) {
        items.forEach {
            HomeMaintenanceWidgetItem(item = it, onClicked = onClickedItem)

            Spacer(modifier = Modifier.size(24.dp))
        }
    }
}

@Composable
fun HomeMaintenanceWidgetItem(
    modifier: Modifier = Modifier,
    item: HomeUiState.Widget.Item,
    onClicked: (String) -> Unit,
) {
    Row(
        modifier = modifier.clickable { onClicked(item.category.route) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            //TODO icon
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Image(
                    painter = when (item.category) {
                        HomeUiState.Widget.Category.Level.Emergency -> painterResource(id = R.drawable.ic_home_maintenance_level_emergency)
                        else -> painterResource(id = R.drawable.ic_home_maintenance_level_normal)
                    },
                    contentDescription = null
                )
                TypoText(
                    text = item.title,
                    typoStyle = TypoStyle.HEADLINE_X_SMALL_14,
                    colorResource = when (item.category) {
                        HomeUiState.Widget.Category.Level.Emergency -> com.ddd.carssok.core.designsystem.R.color.error_text
                        else -> com.ddd.carssok.core.designsystem.R.color.primary_text
                    }
                )
            }

            TypoText(
                text = item.subText,
                typoStyle = TypoStyle.BODY_SMALL_12,
                colorResource = com.ddd.carssok.core.designsystem.R.color.secondary_text,
            )
        }

        Spacer(modifier = Modifier.size(32.dp))
        Image(painter = painterResource(id = com.ddd.carssok.core.designsystem.R.drawable.ic_arrow_circel_right_28), contentDescription = null)
    }
}

internal object HomeColors {
    val gradientTopLight = Color(0xFFEEF0F2)
    val gradientBottomLight = Color(0xFFD2D7DC)

    val gradientTopDark = Color(0xFF313131)
    val gradientBottomDark = Color(0xEB4F4F4F)

    val statusBarColorLight = Color(0xFFFFFFFF)
    val statusBarColorDark = Color(0xFF141414)

    val totalDistanceGradientColors = listOf(
        Color(0xFF099E44), Color(0xFF3CAC73)
    )

    fun getGradientColors(isDark: Boolean): List<Color> {
        return if(isDark) {
            listOf(gradientTopDark, gradientBottomDark)
        } else {
            listOf(gradientTopLight, gradientBottomLight)
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeRoute(
        navigateTo = {},
        navigateToOnBoarding = {},
    )
}