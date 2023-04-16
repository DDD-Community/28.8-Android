package com.ddd.carssok.core.model.record.refuel

data class RefuelEntity(
    val id: Int,
    val date: String,
    val location: String,
    val price: Int,
)

data class DetailRefuelEntity(
    val id: Int,
    val date: String,
    val location: String,
    val price: Int,
    val charge: Int,
    val memo: String,
    val amount: Int,
)

data class RecordRefuelEntity(
    val eventedAt: String,
    val location: String,
    val price: Int,
    val charge: Int,
    val memo: String,
    val amount: Int,
    val fuleType: String,
)