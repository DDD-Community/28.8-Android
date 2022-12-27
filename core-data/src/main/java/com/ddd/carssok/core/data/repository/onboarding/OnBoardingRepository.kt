package com.ddd.carssok.core.data.repository.onboarding

import com.ddd.carssok.core.data.model.OnBoardingModelEntity

interface OnBoardingRepository {

    suspend fun getModels(brand: String): List<OnBoardingModelEntity>
}