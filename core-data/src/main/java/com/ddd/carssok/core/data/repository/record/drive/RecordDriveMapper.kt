package com.ddd.carssok.core.data.repository.record.drive

import com.ddd.carssok.core.model.record.drive.DriveHistoryEntity
import com.ddd.carssok.core.network.model.record.DriveHistoryResponse


fun DriveHistoryResponse.toEntity() = DriveHistoryEntity(
    id = id ?: 0,
    date = date.orEmpty(),
    distance = distance ?: 0
)