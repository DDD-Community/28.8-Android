package com.ddd.carssok.core.network.api

import com.ddd.carssok.core.network.ApiResult
import com.ddd.carssok.core.network.model.BaseModel
import com.ddd.carssok.core.network.model.CarBrandsResponse
import retrofit2.http.GET

interface CarApi {
    @GET("brands")
    suspend fun getCarBrand(): ApiResult<BaseModel<List<CarBrandsResponse>>>
}