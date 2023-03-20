package com.ddd.carssok.feature.onboarding.fuel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.carssok.core.data.Resource
import com.ddd.carssok.core.data.repository.HomeRepositoryImpl
import com.ddd.carssok.core.data.repository.account.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// 임시
enum class FuelType(val koreanName: String) {
    GASOLINE("가솔린"),
    DIESEL("디젤"),
    ELECTRIC("전기"),
    LPG("가스"),
    BULL("수소"),
    HYBRID("하이브리드")
}

@HiltViewModel
class OnBoardingFuelViewModel @Inject constructor(
    private val repository: HomeRepositoryImpl,
    private val accountRepository: AccountRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    data class UiState(
        val carName: String = "",
        val carImageUrl: String = "",
        val fuelList: List<FuelType> = emptyList()
    )

    sealed interface Event {
        object OnSuccessSignup : Event
    }

    init {
        _uiState.update { it.copy(carName = "아반떼 하이브리드(CN7)", fuelList = FuelType.values().toList()) }
    }


    fun onClickedFilterFuel(isSelected: Boolean) {

    }

    fun onClickedDone() = viewModelScope.launch {
        val result = accountRepository.updateRemoteUserToken()
        if (result is Resource.Success) {
            _event.emit(Event.OnSuccessSignup)
        } else {
            // show toast
        }
    }
}