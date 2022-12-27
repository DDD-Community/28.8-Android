package com.ddd.carssok.core.data.model

data class OnBoardingModelEntity(
    val title: String,
    val detailModels: List<OnBoardingDetailModelEntity>
)

data class OnBoardingDetailModelEntity(
    val year: String,
    val title: String,
    val thumbnail: String,
)