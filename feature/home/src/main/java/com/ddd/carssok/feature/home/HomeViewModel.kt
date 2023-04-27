package com.ddd.carssok.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.carssok.core.data.repository.account.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState.SAMPLE)
    val uiState = _uiState.asStateFlow()

    sealed class Event {
        data class CheckedCarssokUser(val isCarssokUser: Boolean) : Event()
    }

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    init {
        checkedCarssokUser()
    }

    private fun checkedCarssokUser() = viewModelScope.launch {
        val result = accountRepository.checkedCarssokuser().date ?: false
        _event.emit(Event.CheckedCarssokUser(result))
    }
}