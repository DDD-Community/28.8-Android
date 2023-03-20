package com.ddd.carssok.feature.onboarding.agreement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.carssok.core.data.model.UserAgreement
import com.ddd.carssok.core.data.repository.agreement.UserAgreementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingUserAgreementViewModel @Inject constructor(
    private val userAgreementRepository: UserAgreementRepository
) : ViewModel() {

    data class UiState(
        val checkList: List<UserAgreement> = emptyList(),
        val buttonEnabled: Boolean = false
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val checkList = userAgreementRepository.getChecklist()
            _uiState.update { it.copy(checkList = checkList) }
        }
    }

    fun onChecked(id: Long, isChecked: Boolean) {
        val currentList = _uiState.value.checkList.toMutableList().apply {
            val index = indexOfFirst { it.id == id }
            this[index] = this[index].copy(isChecked = isChecked)
        }

        val isButtonEnabled = currentList.any { it.isChecked }
        _uiState.update { it.copy(checkList = currentList, buttonEnabled = isButtonEnabled) }
    }

    fun onClickedLink() {

    }

    fun onClickedSave() = viewModelScope.launch {
        userAgreementRepository.save()
    }
}