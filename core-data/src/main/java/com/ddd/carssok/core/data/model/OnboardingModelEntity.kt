package com.ddd.carssok.core.data.model

data class OnboardingModelEntity(
    val title: String,
    val detailModels: List<OnboardingDetailModelEntity>
)

data class OnboardingDetailModelEntity(
    val year: String,
    val title: String,
    val thumbnail: String,
)