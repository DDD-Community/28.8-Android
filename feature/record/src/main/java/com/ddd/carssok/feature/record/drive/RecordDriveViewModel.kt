package com.ddd.carssok.feature.record.drive

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RecordDriveViewModel @Inject constructor(

): ViewModel() {

    private val _mileageState = MutableStateFlow<String>("")
    val mileageState = _mileageState.asStateFlow()

    fun updateMileage(mileage: String) {
        _mileageState.update { mileage }
    }

    fun saveDriveRecord() {

    }
}