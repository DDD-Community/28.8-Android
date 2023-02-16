package com.ddd.carssok.feature.record.drive

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RecordDriveHistoryViewModel @Inject constructor(

) : ViewModel() {


    private val _uiState = MutableStateFlow<RecordDriveHistoryUiState>(RecordDriveHistoryUiState.Normal(year = 2022, month = 12))
    val uiState = _uiState.asStateFlow()

    private val _historyList: MutableStateFlow<List<RecordDriveHistoryEntity>> = MutableStateFlow(dummyList)
    val historyList = _historyList.asStateFlow()

    fun changeEditMode() {
        _uiState.update {
            RecordDriveHistoryUiState.Edit(
                year = it.year,
                month = it.month,
            )
        }
    }

    fun changeNormalMode() {
        _uiState.update {
            RecordDriveHistoryUiState.Normal(
                year = it.year,
                month = it.month,
            )
        }
    }

    fun previousMonth() {
        _uiState.update {
            RecordDriveHistoryUiState.Normal(
                it.year,
                it.month - 1
            )
        }
    }

    fun nextMonth() {
        _uiState.update {
            RecordDriveHistoryUiState.Normal(
                it.year,
                it.month + 1
            )
        }
    }

    fun deleteHistory(historyId: String) {

    }

    sealed interface RecordDriveHistoryUiState {
        val year: Int
        val month: Int

        data class Normal(
            override val year: Int,
            override val month: Int,
        ): RecordDriveHistoryUiState

        data class Edit(
            override val year: Int,
            override val month: Int,
        ): RecordDriveHistoryUiState
    }
}

data class RecordDriveHistoryEntity(
    val id: String,
    val title: String,
    val mileage: Int,
    val day: String,
    val date: String,
)

val dummyList = listOf<RecordDriveHistoryEntity>(
    RecordDriveHistoryEntity(
        id = "0",
        title = "주행 거리",
        mileage = 32,
        day = "월",
        date = "02",
    ),
    RecordDriveHistoryEntity(
        id = "1",
        title = "주행 거리",
        mileage = 260,
        day = "수",
        date = "09",
    ),
    RecordDriveHistoryEntity(
        id = "2",
        title = "주행 거리",
        mileage = 400,
        day = "수",
        date = "16",
    ),
    RecordDriveHistoryEntity(
        id = "3",
        title = "주행 거리",
        mileage = 1200,
        day = "토",
        date = "20",
    ),
)