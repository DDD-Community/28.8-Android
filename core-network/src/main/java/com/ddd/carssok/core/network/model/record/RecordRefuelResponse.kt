package com.ddd.carssok.core.network.model.record

import com.google.gson.annotations.SerializedName

// 주유기록 조회
data class RefuelResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("date") val date: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("price") val price: Int?,
)

// 주유기록 등록
data class RecordRefuelResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("status") val status: String?,
)

// 상세 주유기록 조회
data class RefuelDetailResponse(
    @SerializedName("date") val date: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("price") val price: Int?,
    @SerializedName("charge") val charge: Int?,
    @SerializedName("memo") val memo: String?,
    @SerializedName("amount") val amount: Int?,
)

// 주유기록 삭제
data class DeleteRefuelResponse(
    @SerializedName("status") val status: String?
)

// 주유기록 수정
data class UpdateRefuelResponse(
    @SerializedName("status") val status: String?,
)