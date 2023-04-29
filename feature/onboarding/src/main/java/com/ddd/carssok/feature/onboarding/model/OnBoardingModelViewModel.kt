package com.ddd.carssok.feature.onboarding.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.carssok.core.data.model.OnBoardingDetailModelEntity
import com.ddd.carssok.core.data.model.OnBoardingModelEntity
import com.ddd.carssok.core.data.repository.OnBoardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingModelViewModel @Inject constructor(
    private val repository: OnBoardingRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<OnBoardingModelUiState>(OnBoardingModelUiState.Inputing)
    val uiState = _uiState.asStateFlow()

    fun search(keyword: String) {
        if (keyword.isBlank()) {
            _uiState.update { OnBoardingModelUiState.Inputing }
        } else {
            getModels(brand = keyword)
        }
    }

    private fun getModels(brand: String) = viewModelScope.launch {
        kotlin.runCatching {
//            repository.getModels(brand)
        }.onSuccess { list ->
            _uiState.update { OnBoardingModelUiState.Loaded(modelList = emptyList()) }
        }.onFailure {
            _uiState.update { OnBoardingModelUiState.Error }
        }

    }

    fun onModelSelected(model: OnBoardingDetailModelEntity?) {
        // TODO cache selected model

        _uiState.value.let { state ->
            if (state is OnBoardingModelUiState.Loaded) {
                _uiState.update {
                    state.copy(isNextButtonEnable = model != null)
                }
            }
        }
    }
}

sealed class OnBoardingModelUiState {
    object Inputing: OnBoardingModelUiState()

    data class Loaded(
        val modelList: List<OnBoardingModelEntity>,
        val isNextButtonEnable: Boolean = false
    ): OnBoardingModelUiState()

    object Error: OnBoardingModelUiState()
}