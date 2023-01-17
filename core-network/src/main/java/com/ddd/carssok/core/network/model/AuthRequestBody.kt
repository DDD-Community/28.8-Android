package com.ddd.carssok.core.network.model

import com.google.gson.annotations.SerializedName

data class AuthRequestBody(
    @SerializedName("device_id") val deviceId: String
)