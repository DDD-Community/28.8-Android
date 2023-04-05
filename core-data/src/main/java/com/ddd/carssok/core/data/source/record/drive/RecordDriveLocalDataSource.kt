package com.ddd.carssok.core.data.source.record.drive

import com.ddd.carssok.core.model.record.drive.DriveHistoryEntity
import javax.inject.Inject

class RecordDriveLocalDataSource @Inject constructor() {

    private var historyList: MutableList<DriveHistoryEntity>? = null

    fun getHistoryList(): List<DriveHistoryEntity>? {
        return historyList
    }

    fun setHistoryList(list: List<DriveHistoryEntity>) {
        historyList = list.toMutableList()
    }

    fun deleteHistory(id: Int): Boolean {
        return historyList?.removeIf { it.id == id } ?: false
    }
}