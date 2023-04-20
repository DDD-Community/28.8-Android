package com.ddd.carssok.core.network.model.record

import com.google.gson.annotations.SerializedName

// 주유기록 등록
data class RecordRefuelRequest(
    @SerializedName("eventedAt") val eventedAt: String,
    @SerializedName("location") val location: String,
    @SerializedName("price") val price: Int,
    @SerializedName("charge") val charge: Int,
    @SerializedName("memo") val memo: String,
    @SerializedName("amount") val amount: Int,
    @SerializedName("fuleType") val fuleType: String,
)