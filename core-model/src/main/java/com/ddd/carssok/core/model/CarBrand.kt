package com.ddd.carssok.core.model

data class CarBrand(
    val arrangeOrder: Int,
    val brand: String,
    val createdAt: String,
    val id: Long,
    val isDomestic: Boolean,
    val logo: String,
    val updatedAt: String,
    var isChecked: Boolean = false
)