package com.ddd.carssok.core.designsystem.component

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.ddd.carssok.core.util.DateUtils
import com.ddd.carssok.core.util.DateUtils.toDateString

class DatePickerState(
    private val dialog: DatePickerDialog
) {
    fun onDateSet(
        listener: (datePicker: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int) -> Unit,
    ) {
        dialog.setOnDateSetListener(listener)
    }

    fun updateDate(
        date: String? = null,
    ) {
        val finalDate = date ?: System.currentTimeMillis().toDateString(null)

        dialog.updateDate(
            DateUtils.getYear(finalDate, format = null),
            DateUtils.getMonth(finalDate, format = null) - 1,
            DateUtils.getDay(finalDate, format = null)
        )
    }

    fun show() {
        dialog.show()
    }
}


@Composable
fun rememberDatePicker(
    context: Context,
) = remember {
    DatePickerState(
        DatePickerDialog(context)
    )
}