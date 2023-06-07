package com.ddd.carssok.core.network.model

import com.google.gson.annotations.SerializedName

data class CarBrandsResponse(
    @SerializedName("arrangeOrder") val arrangeOrder: Int,
    @SerializedName("brand") val brand: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("id") val id: Long,
    @SerializedName("isDomestic") val isDomestic: Boolean,
    @SerializedName("logo") val logo: String,
    @SerializedName("updatedAt") val updatedAt: String
)