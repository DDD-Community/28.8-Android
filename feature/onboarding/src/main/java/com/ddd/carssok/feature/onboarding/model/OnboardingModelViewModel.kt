package com.ddd.carssok.feature.onboarding.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.carssok.core.data.model.OnboardingModelEntity
import com.ddd.carssok.core.data.repository.onboarding.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingModelViewModel @Inject constructor(
    private val repository: OnboardingRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<OnboardingModelUiState>(OnboardingModelUiState.Inputing)
    val uiState = _uiState.asStateFlow()

    fun getModels(brand: String) = viewModelScope.launch {
        kotlin.runCatching {
            repository.getModels(brand)
        }.onSuccess { list ->
            _uiState.update { OnboardingModelUiState.Loaded(modelList = list) }
        }.onFailure {
            _uiState.update { OnboardingModelUiState.Error }
        }

    }
}

sealed class OnboardingModelUiState {
    object Inputing: OnboardingModelUiState()
    class Loaded(
        val modelList: List<OnboardingModelEntity>
    ): OnboardingModelUiState()
    object Error: OnboardingModelUiState()
}