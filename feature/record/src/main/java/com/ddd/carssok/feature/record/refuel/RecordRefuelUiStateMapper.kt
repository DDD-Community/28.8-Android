package com.ddd.carssok.feature.record.refuel

import com.ddd.carssok.core.model.record.refuel.DetailRefuelEntity
import com.ddd.carssok.core.model.record.refuel.RecordRefuelEntity
import com.ddd.carssok.core.model.record.refuel.RefuelEntity
import com.ddd.carssok.core.util.StringUtils.toNumberString

fun DetailRefuelEntity.toUiState() = RecordRefuelUiState(
    inputData = RecordRefuelUiState.InputData(
        date = date,
        station = location,
        priceInfo = RecordRefuelUiState.InputData.PriceInfo(
            totalPrice = charge.toNumberString(),
            price = price.toNumberString(),
            amount = amount.toString()
        ),
        memo = memo,
    ),
    mode = RecordRefuelUiState.Mode.Detail
)

fun RecordRefuelUiState.toEntity() = RecordRefuelEntity(
    eventedAt = inputData.date,
    location = inputData.station.orEmpty(),
    price = inputData.priceInfo.price.toInt(),
    charge = inputData.priceInfo.totalPrice.toInt(),
    amount = inputData.priceInfo.amount.toInt(),
    memo = inputData.memo.orEmpty(),
    fuleType = "",
)

fun RefuelEntity.toUiState() = RecordRefuelListUiState.Item(
    id = id,
    title = location,
    date = date,
    price = price,
)