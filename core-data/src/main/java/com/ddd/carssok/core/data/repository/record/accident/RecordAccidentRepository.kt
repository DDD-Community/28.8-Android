package com.ddd.carssok.core.data.repository.record.accident

import android.graphics.BitmapFactory
import com.ddd.carssok.core.data.IoDispatcher
import com.ddd.carssok.core.data.Resource
import com.ddd.carssok.core.model.RecordAccidentEntity
import com.ddd.carssok.core.network.ApiError
import com.ddd.carssok.core.network.ApiException
import com.ddd.carssok.core.network.ApiSuccess
import com.ddd.carssok.core.network.api.RecordApi
import com.ddd.carssok.core.network.model.record.RecordAccidentResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

interface RecordAccidentRepository {
    suspend fun recordAccident(
        eventedAt: String,
        memo: String,
        location: String,
        files: List<File>
    ): Resource<RecordAccidentEntity>
}

class RecordAccidentRepositoryImpl @Inject constructor(
    private val recordApi: RecordApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RecordAccidentRepository {
    override suspend fun recordAccident(
        eventedAt: String,
        memo: String,
        location: String,
        files: List<File>
    ) = withContext(ioDispatcher) {
        val list = mutableListOf(
            MultipartBody.Part.createFormData("eventedAt", eventedAt),
            MultipartBody.Part.createFormData("memo", memo),
            MultipartBody.Part.createFormData("location", location),
        )
        files.forEach {
            BitmapFactory.decodeFile(it.name)
            MultipartBody.Part.createFormData(
                "files", it.name, it.asRequestBody()
            ).let(list::add)
        }

        when (val result = recordApi.recordAccident(list)) {
            is ApiSuccess -> Resource.Success(data = result.data.model.toEntity())
            is ApiError,
            is ApiException -> Resource.Error()
        }
    }
}

fun RecordAccidentResponse.toEntity() = RecordAccidentEntity(id, status)