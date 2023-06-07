package com.ddd.carssok.core.data.repository.record.drive

import com.ddd.carssok.core.data.IoDispatcher
import com.ddd.carssok.core.data.Resource
import com.ddd.carssok.core.data.repository.onboarding.drive.RecordDriveLocalDataSource
import com.ddd.carssok.core.model.record.drive.DriveHistoryEntity
import com.ddd.carssok.core.network.ApiSuccess
import com.ddd.carssok.core.network.api.RecordApi
import com.ddd.carssok.core.network.model.record.RecordDriveRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RecordDriveRepository {

    suspend fun getTotalDistance(): Resource<Int>

    suspend fun getAllHistory(): Resource<List<DriveHistoryEntity>>

    suspend fun record(date: String, distance: Int): Resource<Boolean>

    suspend fun delete(id: Int) : Resource<Boolean>
}

class RecordDriveRepositoryImpl @Inject constructor(
    private val recordApi: RecordApi,
    private val localDataSource: RecordDriveLocalDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RecordDriveRepository {

    override suspend fun getTotalDistance(): Resource<Int> = withContext(ioDispatcher) {
        return@withContext when (val result = recordApi.getTotalDriveDistance()) {
            is ApiSuccess -> {
                Resource.Success(data = result.data.model.totalDistance ?: 0)
            }
            else -> Resource.Error()
        }
    }

    override suspend fun getAllHistory(): Resource<List<DriveHistoryEntity>> = withContext(ioDispatcher) {
        return@withContext localDataSource.getHistoryList()?.let {
            Resource.Success(data = it)
        } ?: run {
            when (val result = recordApi.getAllDrive()) {
                is ApiSuccess -> {
                    val historyList = result.data.model.map { it.toEntity() }.also {
                        localDataSource.setHistoryList(it)
                    }
                    Resource.Success(data = historyList)
                }
                else -> Resource.Error()
            }
        }
    }

    override suspend fun record(date: String, distance: Int): Resource<Boolean> = withContext(ioDispatcher) {
        val request = RecordDriveRequest(
            eventAt = date,
            distance = distance
        )
        return@withContext when (val result = recordApi.recordDrive(request)) {
            is ApiSuccess -> {
                Resource.Success(data = result.data.model.status?.isNotBlank() ?: false)
            }
            else -> Resource.Error()
        }
    }

    override suspend fun delete(id: Int): Resource<Boolean> = withContext(ioDispatcher) {
        return@withContext when (val result = recordApi.deleteDrive(id = id)) {
            is ApiSuccess -> {
                val status = result.data.model.status?.isNotBlank() ?: false
                if (status) {
                    localDataSource.deleteHistory(id)
                }
                Resource.Success(data = status)
            }
            else -> Resource.Error()
        }
    }

}