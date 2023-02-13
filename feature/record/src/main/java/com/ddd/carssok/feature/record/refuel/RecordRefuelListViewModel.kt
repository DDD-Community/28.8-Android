package com.ddd.carssok.feature.record.refuel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordRefuelListViewModel @Inject constructor(

) : ViewModel() {


}

data class RecordRefuelListDetailItemUiState(
    val id: String,
    val title: String,
    val date: String,
    val price: String
)

val dummyList: List<RecordRefuelListDetailItemUiState> = listOf(
    RecordRefuelListDetailItemUiState(
        id = "0",
        title = "GS칼텍스 이케이에너지주유소",
        date = "12.24일 (월)",
        price = "240,520원"
    ),
    RecordRefuelListDetailItemUiState(
        id = "1",
        title = "GS칼텍스 대성산업 동마장주유소",
        date = "12.15일 (월)",
        price = "70,520원"
    ),
    RecordRefuelListDetailItemUiState(
        id = "0",
        title = "SK엔크린 중앙에너비스 혜화",
        date = "12.08 (일)",
        price = "320,520원"
    ),
)