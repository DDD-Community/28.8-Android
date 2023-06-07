package com.ddd.carssok.feature.onboarding.brand

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.carssok.core.data.repository.OnBoardingRepository
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
    private val carBrandsRepository: OnBoardingRepository,
    private val userAgreementRepository: UserAgreementRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    data class UiState(
        val brandItems: List<CarBrand> = emptyList(),
        val nextButtonEnabled: Boolean = false
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
                val brands = carBrandsRepository.getAllCarBrads().date
                _uiState.update { it.copy(brandItems = brands.orEmpty()) }
            }
        }
    }

    fun onCheckedBrandCard(id: Long, isChecked: Boolean) {
        val updateBrands = _uiState.value.brandItems.toMutableList()
        updateBrands.forEach { it.isChecked = false }
        val index = updateBrands.indexOfFirst { it.id == id }
        updateBrands[index] = updateBrands[index].copy(isChecked = isChecked)
        val nextButtonEnabled = updateBrands.any { it.isChecked }
        _uiState.update { it.copy(brandItems = updateBrands, nextButtonEnabled = nextButtonEnabled) }
    }
}