package com.ddd.carssok.core.network.model.record

import com.google.gson.annotations.SerializedName

data class RecordAccidentResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("status") val status: String
)