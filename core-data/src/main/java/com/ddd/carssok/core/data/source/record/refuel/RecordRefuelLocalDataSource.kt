package com.ddd.carssok.core.data.source.record.refuel

import com.ddd.carssok.core.model.record.refuel.DetailRefuelEntity
import com.ddd.carssok.core.model.record.refuel.RefuelEntity
import javax.inject.Inject

class RecordRefuelLocalDataSource @Inject constructor() {

    private var refuelList: MutableList<RefuelEntity>? = null
    private var detailRefuel: DetailRefuelEntity? = null

    fun getRefuelList(): List<RefuelEntity>? {
        return refuelList
    }

    fun setRefuelList(list: List<RefuelEntity>) {
        this.refuelList = list.toMutableList()
    }

    fun deleteRefuel(id: Int): Boolean {
        return refuelList?.removeIf { it.id == id } ?: false
    }

    fun getDetailRefuel(id: Int): DetailRefuelEntity? {
        return detailRefuel?.let {
            if (it.id == id) {
               it
            } else {
                null
            }
        }
    }

    fun setDetailRefuel(refuel: DetailRefuelEntity) {
        this.detailRefuel = refuel
    }
}