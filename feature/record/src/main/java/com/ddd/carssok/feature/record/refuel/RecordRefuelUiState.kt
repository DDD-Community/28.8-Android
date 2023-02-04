package com.ddd.carssok.feature.record.refuel


class RecordRefuelUiState(
    val inputData: RecordRefuelInputData = RecordRefuelInputData.EMPTY
) {
    val isSaveButtonEnable : Boolean
        get() = inputData.date.isNotBlank()
                && inputData.priceInfo.totalPrice.isNotBlank()
                && inputData.priceInfo.price.isNotBlank()
                && inputData.priceInfo.amount.isNotBlank()
}

data class RecordRefuelInputData(
    val date: String,
    val station: String? = null,
    val priceInfo: RecordRefuelPriceInfo,
    val memo: String? = null,
) {
    companion object {
        val EMPTY = RecordRefuelInputData(
            date = "",
            priceInfo = RecordRefuelPriceInfo.EMPTY,
        )
    }
}

data class RecordRefuelPriceInfo(
    val totalPrice: String,
    val price: String,
    val amount: String,
) {
    companion object {
        val EMPTY = RecordRefuelPriceInfo(
            totalPrice = "",
            price = "",
            amount = "",
        )
    }
}