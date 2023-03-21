package com.ddd.carssok.core.data.model

import androidx.annotation.StringRes

data class UserAgreement(val id: Long, @StringRes val messageId: Int, val isChecked: Boolean,@StringRes val link: Int)