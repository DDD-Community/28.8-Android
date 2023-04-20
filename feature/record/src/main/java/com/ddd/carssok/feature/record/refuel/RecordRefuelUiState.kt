package com.ddd.carssok.feature.record.refuel


data class RecordRefuelUiState(
    val inputData: InputData,
    val mode: Mode,
) {
    val isInputMode: Boolean get() = mode is Mode.Input

    val isSaveButtonEnable : Boolean
        get() = inputData.date.isNotBlank()
                && inputData.priceInfo.totalPrice.isNotBlank()
                && inputData.priceInfo.price.isNotBlank()
                && inputData.priceInfo.amount.isNotBlank()

    sealed class Mode {
        object Input: Mode()        // 입력화면
        object Detail: Mode()       // 상세화면
    }

    data class InputData(
        val date: String,
        val station: String? = null,
        val priceInfo: PriceInfo,
        val memo: String? = null,
    ) {
        data class PriceInfo(
            val totalPrice: String,
            val price: String,
            val amount: String,
        ) {
            companion object {
                val EMPTY = PriceInfo(
                    totalPrice = "",
                    price = "",
                    amount = "",
                )
            }
        }

        companion object {
            val EMPTY = InputData(
                date = "",
                priceInfo = PriceInfo.EMPTY,
            )
        }
    }

    companion object {
        val EMPTY = RecordRefuelUiState(
            inputData = InputData.EMPTY,
            mode = Mode.Input
        )
    }
}