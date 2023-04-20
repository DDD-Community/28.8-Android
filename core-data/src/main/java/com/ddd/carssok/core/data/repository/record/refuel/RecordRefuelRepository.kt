package com.ddd.carssok.core.data.repository.record.refuel

import com.ddd.carssok.IoDispatcher
import com.ddd.carssok.core.data.Resource
import com.ddd.carssok.core.data.source.record.refuel.RecordRefuelLocalDataSource
import com.ddd.carssok.core.model.record.refuel.DetailRefuelEntity
import com.ddd.carssok.core.model.record.refuel.RecordRefuelEntity
import com.ddd.carssok.core.model.record.refuel.RefuelEntity
import com.ddd.carssok.core.network.ApiSuccess
import com.ddd.carssok.core.network.api.RecordApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RecordRefuelRepository {

    suspend fun getAllRefuel(date: String): Resource<List<RefuelEntity>>

    suspend fun getRefuelDetail(id: Int): Resource<DetailRefuelEntity>

    suspend fun record(data: RecordRefuelEntity): Resource<Boolean>

    suspend fun delete(id: Int) : Resource<Boolean>
}

class RecordRefuelRepositoryImpl @Inject constructor(
    private val recordApi: RecordApi,
    private val localDataSource: RecordRefuelLocalDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): RecordRefuelRepository {

    override suspend fun getAllRefuel(date: String): Resource<List<RefuelEntity>> = withContext(ioDispatcher) {
        return@withContext localDataSource.getRefuelList()?.let {
            Resource.Success(data = it)
        } ?: run {
            when (val result = recordApi.getAllRefuel("")) {
                is ApiSuccess -> {
                    val refuelList = result.data.model.map { it.toEntity() }.also {
                        localDataSource.setRefuelList(it)
                    }
                    Resource.Success(data = refuelList)
                }
                else -> Resource.Error()
            }
        }
    }

    override suspend fun getRefuelDetail(id: Int): Resource<DetailRefuelEntity> = withContext(ioDispatcher) {
        return@withContext localDataSource.getDetailRefuel(id = id)?.let {
            Resource.Success(data = it)
        } ?: run {
            when (val result = recordApi.getRefuelDetail(id = id)) {
                is ApiSuccess -> {
                    val detailRefuel = result.data.model.let {
                            it.toEntity(id = id)
                        }.also {
                            localDataSource.setDetailRefuel(it)
                        }
                    Resource.Success(data = detailRefuel)
                }
                else -> Resource.Error()
            }
        }
    }

    override suspend fun record(request: RecordRefuelEntity): Resource<Boolean> = withContext(ioDispatcher) {
        return@withContext when (val result = recordApi.recordRefuel(request.toRequest())) {
            is ApiSuccess -> {
                Resource.Success(data = result.data.model.status?.isNotBlank() ?: false)
            }
            else -> Resource.Error()
        }
    }

    override suspend fun delete(id: Int): Resource<Boolean> = withContext(ioDispatcher) {
        return@withContext when (val result = recordApi.deleteRefuel(id = id)) {
            is ApiSuccess -> {
                val status = result.data.model.status?.isNotBlank() ?: false
                if (status) {
                    localDataSource.deleteRefuel(id)
                }
                Resource.Success(data = status)
            }
            else -> Resource.Error()
        }
    }

}