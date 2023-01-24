package com.ddd.carssok.core.network.model

import com.google.gson.annotations.SerializedName

data class UserTokenResponse(
    @SerializedName("useToken") val userToken: String?
)