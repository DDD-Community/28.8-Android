package com.ddd.carssok.feature.onboarding

import androidx.lifecycle.ViewModel
import com.ddd.carssok.core.data.repository.HomeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val repository: HomeRepositoryImpl
): ViewModel() {

    fun onOnboardingDone() {
        repository.needShowOnboarding = false
    }
}