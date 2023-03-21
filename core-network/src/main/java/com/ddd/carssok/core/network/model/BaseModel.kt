package com.ddd.carssok.core.network.model

import com.google.gson.annotations.SerializedName

data class BaseModel<T>(
    @SerializedName("data") val model: T,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: String,
)