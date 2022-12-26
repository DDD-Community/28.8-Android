package com.ddd.carssok.feature.onboarding.brand

import androidx.lifecycle.ViewModel
import com.ddd.carssok.core.model.CarBrand
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OnBoardingBrandViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    data class UiState(
        val brandItems: List<CarBrand> = emptyList(),
        val nextButtonEnabled: Boolean = true
    )

    init {
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

    fun onCheckedBrandCard(id: Long, isChecked: Boolean) {

    }
}