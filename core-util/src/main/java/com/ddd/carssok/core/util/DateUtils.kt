package com.ddd.carssok.core.util

import androidx.annotation.StringRes
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {
    private const val DEFAULT_FORMAT_DATE_WITHOUT_TIME = "yyyy-MM-dd"

    fun Long.toDateString(format: String?): String {
        return try {
            Date(this).formatDate(format)
        } catch (e: Exception) {
            e.printStackTrace()
            this.toString()
        }
    }

    fun getPreviousMonth(month: Int): Int {
        val c = Calendar.getInstance().apply {
            set(Calendar.MONTH, month - 1) // 0 for January
            add(Calendar.MONTH, -1)
        }
        return getMonth(c.time)
    }

    fun getNextMonth(month: Int): Int {
        val c = Calendar.getInstance().apply {
            set(Calendar.MONTH, month - 1) // 0 for January
            add(Calendar.MONTH, +1)
        }
        return getMonth(c.time)
    }

    @StringRes
    fun getWeekDayString(date: String, format: String?): Int {
        return when (getWeekDay(date, format)) {
            Calendar.MONDAY -> R.string.week_day_mon
            Calendar.TUESDAY -> R.string.week_day_tue
            Calendar.WEDNESDAY -> R.string.week_day_wed
            Calendar.THURSDAY -> R.string.week_day_thu
            Calendar.FRIDAY -> R.string.week_day_fri
            Calendar.SATURDAY -> R.string.week_day_sat
            Calendar.SUNDAY -> R.string.week_day_sun
            else -> R.string.week_day_mon
        }
    }

    fun getCurrentYear(): Int {
        return getYear(Date(System.currentTimeMillis()))
    }

    fun getCurrentMonth(): Int {
        return getMonth(Date(System.currentTimeMillis()))
    }

    fun getYear(date: Date): Int {
        val c = Calendar.getInstance().apply {
            time = date
        }
        return c[Calendar.YEAR]
    }

    fun getYear(date: String, format: String?): Int {
        return getYear(date.formatDate(format))
    }

    fun getMonth(date: Date): Int {
        val c = Calendar.getInstance().apply {
            time = date
        }
        return c[Calendar.MONTH] + 1
    }

    fun getMonth(date: String, format: String?): Int {
        return getMonth(
            date.formatDate(format)
        )
    }

    fun getDay(date: Date): Int {
        val c = Calendar.getInstance().apply {
            time = date
        }
        return c[Calendar.DAY_OF_MONTH]
    }

    fun getDay(date: String, format: String?): Int {
        return getDay(date.formatDate(format))
    }

    fun getWeekDay(date: Date?): Int {
        val c = Calendar.getInstance().apply {
            time = date
        }
        return c[Calendar.DAY_OF_WEEK]
    }

    fun getWeekDay(date: String, format: String?): Int {
        return getWeekDay(date.formatDate(format))
    }

    fun Date.formatDate(formatStr: String?): String {
        return SimpleDateFormat(formatStr ?: DEFAULT_FORMAT_DATE_WITHOUT_TIME, Locale.KOREAN).format(this)
    }

    fun String.formatDate(formatStr: String?): Date {
        return SimpleDateFormat(formatStr ?: DEFAULT_FORMAT_DATE_WITHOUT_TIME, Locale.KOREAN).parse(this)
    }

}