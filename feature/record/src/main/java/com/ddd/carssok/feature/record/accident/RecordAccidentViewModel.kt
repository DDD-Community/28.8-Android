package com.ddd.carssok.feature.record.accident

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.carssok.core.data.Resource
import com.ddd.carssok.core.data.repository.record.accident.RecordAccidentRepository
import com.ddd.carssok.core.util.DateUtils
import com.ddd.carssok.core.util.DateUtils.toDateString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class RecordAccidentViewModel @Inject constructor(
    private val repository: RecordAccidentRepository
) : ViewModel() {
    data class UiState(
        val isLoading: Boolean = false,
        val date: String = System.currentTimeMillis().toDateString(null),
        val memo: String = "",
        val locationMemo: String = "",
        val picture: List<Uri> = emptyList()
    ) {
        val year: Int = DateUtils.getYear(date, null)
        val month: Int = DateUtils.getMonth(date, null)
        val day: Int = DateUtils.getDay(date, null)
    }

    sealed class Event {
        object OnSuccess : Event()
    }

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    fun updateDate(date: String) {
        _uiState.update { it.copy(date = date) }
    }

    fun updateMemo(input: String) {
        _uiState.update { it.copy(memo = input) }
    }

    fun updateLocationMemo(input: String) {
        _uiState.update { it.copy(locationMemo = input) }
    }

    fun addPicture(picture: Uri) {
        val list = _uiState.value.picture.toMutableList()
        if (list.count() >= 5) return
        list.add(picture)
        _uiState.update { it.copy(picture = list) }
    }

    fun removePicture(index: Int) {
        val list = _uiState.value.picture.toMutableList().apply {
            removeAt(index)
        }
        _uiState.update { it.copy(picture = list) }
    }

    fun saveRecordAccident(files: List<File>) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        val uiState = _uiState.value
        when (repository.recordAccident(uiState.date, uiState.memo, uiState.locationMemo, files)) {
            is Resource.Success -> {
                _event.emit(Event.OnSuccess)
            }
            else -> {

            }
        }.also {
            _uiState.update { it.copy(isLoading = false) }
        }
    }
}