package com.ddd.carssok.core.data.repository.record.refuel

import com.ddd.carssok.core.model.record.refuel.DetailRefuelEntity
import com.ddd.carssok.core.model.record.refuel.RecordRefuelEntity
import com.ddd.carssok.core.model.record.refuel.RefuelEntity
import com.ddd.carssok.core.network.model.record.RecordRefuelRequest
import com.ddd.carssok.core.network.model.record.RefuelDetailResponse
import com.ddd.carssok.core.network.model.record.RefuelResponse

// 주유 정보
fun RefuelResponse.toEntity() = RefuelEntity(
    id = id ?: 0,
    date = date.orEmpty(),
    location = location.orEmpty(),
    price = price ?: 0,
)

// 주유 상세 정보
fun RefuelDetailResponse.toEntity(id: Int) = DetailRefuelEntity(
    id = id,
    date = date.orEmpty(),
    location = location.orEmpty(),
    price = price ?: 0,
    charge = charge ?: 0,
    memo = memo.orEmpty(),
    amount = amount ?: 0,
)

fun RecordRefuelEntity.toRequest() = RecordRefuelRequest(
    eventedAt = eventedAt,
    location = location,
    price = price,
    charge = charge,
    memo = memo,
    amount = amount,
    fuleType = fuleType,
)