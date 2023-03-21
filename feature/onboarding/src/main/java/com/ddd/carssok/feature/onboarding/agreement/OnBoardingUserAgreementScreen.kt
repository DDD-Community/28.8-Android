package com.ddd.carssok.feature.onboarding.agreement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddd.carssok.core.data.model.UserAgreement
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.CarssockCheckbox
import com.ddd.carssok.core.designsystem.component.CarssokButton
import com.ddd.carssok.core.designsystem.component.TypoText
import com.ddd.carssok.feature.onboarding.R


@Composable
fun OnBoardingUserAgreementRoute(
    viewModel: OnBoardingUserAgreementViewModel = hiltViewModel(),
    done: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    OnBoardingUserAgreementScreen(state.checkList, state.buttonEnabled, viewModel::onChecked, onClickedSave = {
        viewModel.onClickedSave()
        done()
    })
}

@Composable
fun OnBoardingUserAgreementScreen(
    checkList: List<UserAgreement>,
    buttonEnabled: Boolean,
    onChangedCheckBox: (Long, Boolean) -> Unit,
    onClickedSave: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_bg))
            .padding(horizontal = 24.dp, vertical = 52.dp)
            .fillMaxSize()
    ) {
        OnBoardingUserAgreementHeader()
        OnBoardingUserAgreementCheckbox(checkList, onChangedCheckBox)
        Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.BottomCenter) {
            CarssokButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                isEnabled = buttonEnabled,
                titleRes = R.string.on_boarding_user_agreement_ok,
                onClicked = onClickedSave
            )
        }
    }
}

@Composable
fun OnBoardingUserAgreementHeader() {
    TypoText(
        text = stringResource(id = R.string.on_boarding_title),
        typoStyle = TypoStyle.DISPLAY_SMALL_24,
        color = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_text)
    )
    TypoText(
        text = stringResource(id = R.string.on_boarding_subtitle),
        typoStyle = TypoStyle.HEADLINE_X_SMALL_14,
        color = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_text)
    )
}

@Composable
fun OnBoardingUserAgreementCheckbox(checkList: List<UserAgreement>, onChangedCheckBox: (Long, Boolean) -> Unit) {
    TypoText(
        text = stringResource(id = R.string.on_boarding_require_check),
        typoStyle = TypoStyle.HEADLINE_X_SMALL_14,
        modifier = Modifier.padding(top = 44.dp)
    )
    LazyColumn(modifier = Modifier.padding()) {
        itemsIndexed(checkList, key = { _: Int, item: UserAgreement -> item.id }) { _, item ->
            Row(modifier = Modifier.height(48.dp), verticalAlignment = Alignment.CenterVertically) {
                CarssockCheckbox(isChecked = item.isChecked, onChangedValue = {
                    onChangedCheckBox(item.id, it)
                })
                Spacer(modifier = Modifier.width(14.dp))
                TypoText(
                    text = stringResource(id = item.messageId), typoStyle = TypoStyle.HEADLINE_SMALL_16
                )
            }
        }
    }
}

@Preview
@Composable
fun OnBoardingUserAgreementRoutePreview() {
    OnBoardingUserAgreementRoute {

    }
}