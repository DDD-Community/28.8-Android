package com.ddd.carssok.feature.record.refuel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.carssok.core.data.repository.record.refuel.RecordRefuelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordRefuelViewModel @Inject constructor(
    private val repository: RecordRefuelRepository,
): ViewModel() {

    private val _uiState = MutableStateFlow(RecordRefuelUiState.EMPTY)
    val uiState: StateFlow<RecordRefuelUiState> = _uiState.asStateFlow()

    fun initialize(id: Int) = viewModelScope.launch {
        if (id > -1) {
            val result = repository.getRefuelDetail(id = id).date

            result?.let {
                _uiState.update {
                    result.toUiState()
                }
            }
        }
    }

    fun updateInputDate(date: String) {
        _uiState.update {
            it.copy(
                inputData = it.inputData.copy(
                    date = date
                )
            )
        }
    }

    fun updateInputData(data: RecordRefuelUiState.InputData) {
        _uiState.update {
            it.copy(
                inputData = data
            )
        }
    }

    fun recordRefuelHistory() = viewModelScope.launch {
        val result = repository.record(
            data = _uiState.value.toEntity()
        ).date ?: false

        if (result) {
            _uiState.update {
                RecordRefuelUiState.EMPTY
            }
        }
    }
}