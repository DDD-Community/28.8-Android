package com.ddd.carssok.feature.onboarding.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.carssok.core.data.model.OnBoardingModelEntity
import com.ddd.carssok.core.data.repository.onboarding.OnBoardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingModelViewModel @Inject constructor(
    private val repository: OnBoardingRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<OnBoardingModelUiState>(OnBoardingModelUiState.Inputing)
    val uiState = _uiState.asStateFlow()

    fun getModels(brand: String) = viewModelScope.launch {
        kotlin.runCatching {
            repository.getModels(brand)
        }.onSuccess { list ->
            _uiState.update { OnBoardingModelUiState.Loaded(modelList = list) }
        }.onFailure {
            _uiState.update { OnBoardingModelUiState.Error }
        }

    }
}

sealed class OnBoardingModelUiState {
    object Inputing: OnBoardingModelUiState()
    class Loaded(
        val modelList: List<OnBoardingModelEntity>
    ): OnBoardingModelUiState()
    object Error: OnBoardingModelUiState()
}