package com.ddd.carssok.feature.record.refuel

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.carssok.core.data.repository.record.refuel.RecordRefuelRepository
import com.ddd.carssok.core.model.record.refuel.RefuelEntity
import com.ddd.carssok.core.util.DateUtils
import com.ddd.carssok.core.util.DateUtils.toDateString
import com.ddd.carssok.core.util.StringUtils.toNumberString
import com.ddd.carssok.feature.record.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordRefuelListViewModel @Inject constructor(
    private val repository: RecordRefuelRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecordRefuelListUiState.EMPTY)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val currentYear = DateUtils.getCurrentYear()
            val currentMonth = DateUtils.getCurrentMonth()

            _uiState.update {
                it.copy(
                    selectedYear = currentYear,
                    selectedMonth = currentMonth,
                    list = getFilteredHistoryList(currentYear, currentMonth).map { item -> item.toUiState() }
                )
            }
        }
    }

    private suspend fun getFilteredHistoryList(year: Int, month: Int): List<RefuelEntity> {
        val currentDate = System.currentTimeMillis().toDateString(null)
        val historyList = repository.getAllRefuel(currentDate).date

        return historyList
            ?.filter {
                DateUtils.getYear(currentDate, null) == year
                &&
                DateUtils.getMonth(currentDate, null) == month
            }
            ?: emptyList()
    }

    fun changeEditMode() {
        _uiState.update {
            it.copy(
                mode = RecordRefuelListUiState.Mode.Edit
            )
        }
    }

    fun changeNormalMode() {
        _uiState.update {
            it.copy(
                mode = RecordRefuelListUiState.Mode.Normal
            )
        }
    }

    fun deleteHistory(id: Int) = viewModelScope.launch {
        val result = repository.delete(id = id).date

        _uiState.update {
            it.copy(
                list = getFilteredHistoryList(it.selectedYear, it.selectedMonth).map { item -> item.toUiState() }
            )
        }
    }

}

data class RecordRefuelListUiState(
    val list: List<Item>,
    val mode: Mode,
    val selectedYear: Int,
    val selectedMonth: Int,
) {
    val isEditMode: Boolean get() = mode is Mode.Edit

    val monthlyTotalPriceText: String get() = list.sumOf { it.price }.toNumberString()

    data class Item(
        val id: Int,
        val title: String,
        val date: String,
        val price: Int,
    ) {
        @get:StringRes
        val dateResId get() = R.string.record_refuel_list_item_date

        val month get() = DateUtils.getMonth(date, null)

        val day get() = DateUtils.getDay(date, null)

        @get:StringRes
        val weekDayResId get() = DateUtils.getWeekDayString(date, null)

        val priceText = price.toNumberString()
    }

    sealed class Mode {
        object Normal: Mode()
        object Edit: Mode()
    }

    companion object {
        val EMPTY = RecordRefuelListUiState(
            list = emptyList(),
            mode = Mode.Normal,
            selectedYear = 0,
            selectedMonth = 0,
        )
    }
}

