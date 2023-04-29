package com.ddd.carssok.core.data.repository

import com.ddd.carssok.core.data.IoDispatcher
import com.ddd.carssok.core.data.Resource
import com.ddd.carssok.core.model.CarBrand
import com.ddd.carssok.core.network.ApiSuccess
import com.ddd.carssok.core.network.api.CarApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface OnBoardingRepository {
    suspend fun getAllCarBrads(): Resource<List<CarBrand>>
    suspend fun getAllCarModel(): Resource<List<CarBrand>>
    fun selectedBrand(id: Long)
    fun selectedDetail(id: Long)
    fun selectedCarModel(id: Long)
}

class OnBoardingRepositoryImpl @Inject constructor(
    private val carApi: CarApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : OnBoardingRepository {
    override suspend fun getAllCarBrads(): Resource<List<CarBrand>> = withContext(ioDispatcher) {
        when (val result = carApi.getCarBrand()) {
            is ApiSuccess -> {
                Resource.Success(result.data.model.map {
                    CarBrand(
                        it.arrangeOrder,
                        it.brand,
                        it.createdAt,
                        it.id,
                        it.isDomestic,
                        it.logo,
                        it.updatedAt
                    )
                })
            }
            else -> Resource.Error()
        }
    }

    override suspend fun getAllCarModel(): Resource<List<CarBrand>> {
        return Resource.Error()
    }

    override fun selectedBrand(id: Long) {

    }

    override fun selectedDetail(id: Long) {

    }

    override fun selectedCarModel(id: Long) {

    }
}