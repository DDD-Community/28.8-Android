package com.ddd.carssok.core.network.model.record

import com.google.gson.annotations.SerializedName

// 주행기록 조회
data class DriveHistoryResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("date") val date: String?,
    @SerializedName("distance") val distance: Int?,
)

// 전체 주행거리 조회
data class TotalDriveDistanceResponse(
    @SerializedName("distance") val totalDistance: Int?,
)

// 주행기록 등록
data class RecordDriveResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("status") val status: String?,
)

// 주행기록 삭제
data class DeleteDriveResponse(
    @SerializedName("status") val status: String?,
)

// 주행기록 수정
data class UpdateDriveResponse(
    @SerializedName("status") val status: String?,
)