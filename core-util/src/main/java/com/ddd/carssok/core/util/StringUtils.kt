package com.ddd.carssok.core.util

import java.text.DecimalFormat

object StringUtils {
    private const val FORMAT_DECIMAL = "#,###"

    fun Int.toNumberString(): String {
        return DecimalFormat(FORMAT_DECIMAL).format(this)
    }
}