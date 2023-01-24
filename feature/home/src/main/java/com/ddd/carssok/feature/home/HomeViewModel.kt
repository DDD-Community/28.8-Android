package com.ddd.carssok.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.carssok.core.data.repository.account.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    sealed class Event {
        data class CheckedCarssokUser(val isCarssokUser:Boolean) : Event()
    }

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    init {
        isCarssokUser()
    }

    private fun isCarssokUser() = viewModelScope.launch {
        accountRepository.checkedCarssokuser()
        val fakeValue = false

        _event.emit(Event.CheckedCarssokUser(fakeValue))
    }
}