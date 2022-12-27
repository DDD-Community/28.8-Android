package com.ddd.carssok.core.data.repository.onboarding

import com.ddd.carssok.core.data.model.OnboardingModelEntity

interface OnboardingRepository {

    suspend fun getModels(brand: String): List<OnboardingModelEntity>
}