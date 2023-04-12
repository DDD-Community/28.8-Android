package com.ddd.carssok.feature.record.drive

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.carssok.core.data.repository.record.drive.RecordDriveRepository
import com.ddd.carssok.core.model.record.drive.DriveHistoryEntity
import com.ddd.carssok.core.util.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordDriveHistoryViewModel @Inject constructor(
    private val repository: RecordDriveRepository
) : ViewModel() {


    private val _uiState = MutableStateFlow(RecordDriveHistoryUiState.EMPTY)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val currentYear = DateUtils.getCurrentYear()
            val currentMonth = DateUtils.getCurrentMonth()

            _uiState.update {
                it.copy(
                    year = currentYear,
                    month = currentMonth,
                    historyList = getFilteredHistoryList(currentYear, currentMonth).map { it.toUiState() }
                )
            }
        }
    }

    fun changeEditMode() {
        _uiState.update {
            it.copy(
                year = it.year,
                month = it.month,
                mode = RecordDriveHistoryUiState.Mode.Edit
            )
        }
    }

    fun changeNormalMode() {
        _uiState.update {
            it.copy(
                year = it.year,
                month = it.month,
                mode = RecordDriveHistoryUiState.Mode.Normal
            )
        }
    }

    fun previousMonth() = viewModelScope.launch {
        _uiState.update {
            val prevMonth = DateUtils.getPreviousMonth(it.month)
            it.copy(
                year = it.year,
                month = prevMonth,
                historyList = getFilteredHistoryList(it.year, prevMonth).map { it.toUiState() }
            )
        }
    }

    fun nextMonth() = viewModelScope.launch {
        _uiState.update {
            val nexMonth = DateUtils.getNextMonth(it.month)
            it.copy(
                year = it.year,
                month = nexMonth,
                historyList = getFilteredHistoryList(it.year, nexMonth).map { it.toUiState() }
            )
        }
    }

    fun deleteHistory(id: Int) = viewModelScope.launch {
        val result = repository.delete(id = id)
        _uiState.update {
            it.copy(
                historyList = getFilteredHistoryList(it.year, it.month).map { it.toUiState() }
            )
        }
    }

    private suspend fun getFilteredHistoryList(year: Int, month: Int): List<DriveHistoryEntity> {
        val historyList = repository.getAllHistory().date

        return historyList
            ?.filter { it ->
                DateUtils.getYear(it.date, null) == year
                &&
                DateUtils.getMonth(it.date, null) == month
            }
            ?: emptyList()
    }

}

data class RecordDriveHistoryUiState(
    val year: Int,
    val month: Int,
    val historyList: List<RecordDriveHistoryItemUiState>,
    val mode: Mode,
) {
    sealed class Mode {
        object Normal: Mode()
        object Edit: Mode()
    }

    companion object {
        val EMPTY = RecordDriveHistoryUiState(
            year = 0,
            month = 0,
            historyList = emptyList(),
            mode = Mode.Normal
        )
    }
}

data class RecordDriveHistoryItemUiState(
    val id: Int,
    val distance: Int,
    @StringRes val weekDayResId: Int,
    val date: String,
)

fun DriveHistoryEntity.toUiState() = RecordDriveHistoryItemUiState(
    id = id,
    distance = distance,
    weekDayResId = DateUtils.getWeekDayString(date, null),
    date = DateUtils.getDay(date, null).toString(),
)