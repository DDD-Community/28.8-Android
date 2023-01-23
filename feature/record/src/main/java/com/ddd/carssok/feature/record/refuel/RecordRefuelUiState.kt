package com.ddd.carssok.feature.record.refuel

data class RecordRefuelInputData(
    val date: String,
    val station: String? = null,
    val totalPrice: String,
    val price: String,
    val amount: String,
    val memo: String? = null,
) {
    fun isNotBlank(): Boolean =
        date.isNotBlank()
            && totalPrice.isNotBlank()
            && price.isNotBlank()
            && amount.isNotBlank()

    companion object {
        val EMPTY = RecordRefuelInputData(
            date = "",
            totalPrice = "",
            price = "",
            amount = "",
        )
    }
}