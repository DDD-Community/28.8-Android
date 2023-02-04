package com.ddd.carssok.feature.record.refuel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RecordRefuelViewModel @Inject constructor(

): ViewModel() {

    private val _uiState = MutableStateFlow(RecordRefuelUiState())
    val uiState: StateFlow<RecordRefuelUiState> = _uiState.asStateFlow()

    fun updateInputData(data: RecordRefuelInputData) {
        _uiState.update { RecordRefuelUiState(inputData = data) }
    }

    fun saveInputData() {

    }
}