package com.ddd.carssok.feature.home

import androidx.lifecycle.ViewModel
import com.ddd.carssok.core.data.repository.HomeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepositoryImpl
) : ViewModel() {

    var onboardingState = repository.needShowOnboarding
}