package com.ddd.carssok.core.network.api

import com.ddd.carssok.core.network.ApiResult
import com.ddd.carssok.core.network.model.BaseModel
import com.ddd.carssok.core.network.model.record.DeleteDriveResponse
import com.ddd.carssok.core.network.model.record.DeleteRefuelResponse
import com.ddd.carssok.core.network.model.record.DriveHistoryResponse
import com.ddd.carssok.core.network.model.record.RecordDriveRequest
import com.ddd.carssok.core.network.model.record.RecordDriveResponse
import com.ddd.carssok.core.network.model.record.RecordRefuelRequest
import com.ddd.carssok.core.network.model.record.RecordRefuelResponse
import com.ddd.carssok.core.network.model.record.RefuelDetailResponse
import com.ddd.carssok.core.network.model.record.RefuelResponse
import com.ddd.carssok.core.network.model.record.TotalDriveDistanceResponse
import com.ddd.carssok.core.network.model.record.UpdateDriveResponse
import com.ddd.carssok.core.network.model.record.UpdateRefuelResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RecordApi {
    // 주행
    @GET("/record/runs")
    suspend fun getAllDrive(): ApiResult<BaseModel<List<DriveHistoryResponse>>>

    @GET("/record/runs/total-distance")
    suspend fun getTotalDriveDistance(): ApiResult<BaseModel<TotalDriveDistanceResponse>>

    @POST("/record/runs")
    suspend fun recordDrive(
        @Body request: RecordDriveRequest
    ): ApiResult<BaseModel<RecordDriveResponse>>

    @DELETE("/record/runs/{id}")
    suspend fun deleteDrive(
        @Path("id") id: Int
    ): ApiResult<BaseModel<DeleteDriveResponse>>

    @PUT("record/runs/{id}")
    suspend fun updateDrive(
        @Path("id") id: String,
        @Body request: RecordDriveRequest
    ): ApiResult<BaseModel<UpdateDriveResponse>>

    // 주유
    @GET("/record/fuels")
    suspend fun getAllRefuel(
        @Query("date") date: String,
    ): ApiResult<BaseModel<List<RefuelResponse>>>

    @GET("/record/fuels/{id}")
    suspend fun getRefuelDetail(
        @Path("id") id: Int,
    ): ApiResult<BaseModel<RefuelDetailResponse>>

    @POST("/record/fuels")
    suspend fun recordRefuel(
        @Body request: RecordRefuelRequest
    ): ApiResult<BaseModel<RecordRefuelResponse>>

    @DELETE("/record/fuels/{id}")
    suspend fun deleteRefuel(
        @Path("id") id: Int,
    ): ApiResult<BaseModel<DeleteRefuelResponse>>

    @PUT("/record/fuels/{id}")
    suspend fun updateRefuel(
        @Path("id") id: Int,
        @Body request: RecordRefuelRequest
    ): ApiResult<BaseModel<UpdateRefuelResponse>>
}