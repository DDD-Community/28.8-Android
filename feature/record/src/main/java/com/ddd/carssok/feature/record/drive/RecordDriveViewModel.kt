package com.ddd.carssok.feature.record.drive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.carssok.core.data.repository.record.drive.RecordDriveRepository
import com.ddd.carssok.core.util.DateUtils.toDateString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordDriveViewModel @Inject constructor(
    private val repository: RecordDriveRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(RecordDriveUiState.EMPTY)
    val uiState = _uiState.asStateFlow()

    init {
        init()
    }

    private fun init() = viewModelScope.launch {
        val totalDistance = repository.getTotalDistance().date
        _uiState.update {
            it.copy(
                totalDistance = totalDistance ?: 0,
                date = System.currentTimeMillis().toDateString(format = null),
                distance = "",
            )
        }
    }

    fun updateDistance(distance: String) {
        _uiState.update {
            it.copy(
                distance = distance
            )
        }
    }

    fun updateDate(date: String) {
        _uiState.update {
            it.copy(
                date = date
            )
        }
    }

    fun recordDriveHistory() = viewModelScope.launch {
        repository.record(
            date = _uiState.value.date,
            distance = _uiState.value.distance.toInt(),
        )

        init()
    }
}

data class RecordDriveUiState(
    val totalDistance: Int,
    val date: String,
    val distance: String
) {
    companion object {
        val EMPTY = RecordDriveUiState(
            totalDistance = 0,
            date = System.currentTimeMillis().toDateString(null),
            distance = "",
        )
    }
}