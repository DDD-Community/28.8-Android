package com.ddd.carssok.feature.onboarding.brand

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.carssok.core.data.repository.account.AccountRepository
import com.ddd.carssok.core.data.repository.agreement.UserAgreementRepository
import com.ddd.carssok.core.model.CarBrand
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingBrandViewModel @Inject constructor(
    private val authRepository: AccountRepository,
    private val userAgreementRepository: UserAgreementRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    data class UiState(
        val brandItems: List<CarBrand> = emptyList(),
        val nextButtonEnabled: Boolean = true
    )

    sealed interface Event {
        object NeedAgreement : Event
    }

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    init {
        viewModelScope.launch {
            val isPass = userAgreementRepository.checkedUserAgreement().first()
            if (isPass.not()) {
                _event.emit(Event.NeedAgreement)
            } else {
                val dummy =
                    listOf(
                        CarBrand(1, "기아", false),
                        CarBrand(2, "현대", false),
                        CarBrand(3, "르노", false),
                        CarBrand(4, "쌍용", false),
                        CarBrand(5, "제네시스", false),
                        CarBrand(6, "제네시스", false)
                    )
                _uiState.update { it.copy(brandItems = dummy) }
            }
        }

        viewModelScope.launch {
            kotlin.runCatching {
                //TODO 임시방편 코루틴 error 공통 사용 개발 필요
//                authRepository.getDeviceUserToken()
            }
        }
    }

    fun onCheckedBrandCard(id: Long, isChecked: Boolean) {

    }
}