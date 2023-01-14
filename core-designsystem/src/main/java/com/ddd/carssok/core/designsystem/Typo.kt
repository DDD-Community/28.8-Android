package com.ddd.carssok.core.designsystem

import androidx.compose.ui.text.font.FontWeight

enum class TypoStyle(val textSize: Int, val fontWeight: FontWeight) {
    DISPLAY_X_LARGE_44(44, FontWeight.Bold),
    DISPLAY_LARGE_32(32, FontWeight.Bold),
    DISPLAY_MEDIUM_28(28, FontWeight.Bold),
    DISPLAY_SMALL_24(24, FontWeight.Bold),

    HEADLINE_LARGE_20(20, FontWeight.Bold),
    HEADLINE_MEDIUM_18(18, FontWeight.Bold),
    HEADLINE_SMALL_16(16, FontWeight.Bold),
    HEADLINE_X_SMALL_14(14, FontWeight.Normal),

    BODY_LARGE_16(16, FontWeight.Normal),
    BODY_MEDIUM_14(14, FontWeight.Normal),
    BODY_SMALL_12(12, FontWeight.Normal),
    BODY_X11_SMALL(11, FontWeight.Normal),
}