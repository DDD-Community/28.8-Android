package com.ddd.carssok.core.network.model.record

import com.google.gson.annotations.SerializedName

// 주행기록 등록
data class RecordDriveRequest(
    @SerializedName("eventedAt") val eventAt: String,
    @SerializedName("distance") val distance: Int,
)