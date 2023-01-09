package com.ddd.carssok.feature.onboarding.fuel

import androidx.lifecycle.ViewModel
import com.ddd.carssok.core.data.repository.HomeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
class OnBoardingFuelViewModel @Inject constructor(private val repository: HomeRepositoryImpl) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    data class UiState(
        val carName: String = "",
        val carImageUrl: String = "",
        val fuelList: List<FuelType> = emptyList()
    )

    init {
        _uiState.update { it.copy(carName = "아반떼 하이브리드(CN7)", fuelList = FuelType.values().toList()) }
    }


    fun onClickedFilterFuel(isSelected: Boolean) {

    }

    fun onClickedDone() {
        repository.needShowOnBoarding = false
    }
}