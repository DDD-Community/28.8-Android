package com.ddd.carssok.core.network.api

import com.ddd.carssok.core.network.ApiResult
import com.ddd.carssok.core.network.model.BaseModel
import com.ddd.carssok.core.network.model.record.DeleteDriveResponse
import com.ddd.carssok.core.network.model.record.DriveHistoryResponse
import com.ddd.carssok.core.network.model.record.RecordDriveRequest
import com.ddd.carssok.core.network.model.record.RecordDriveResponse
import com.ddd.carssok.core.network.model.record.TotalDriveDistanceResponse
import com.ddd.carssok.core.network.model.record.UpdateDriveResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RecordApi {

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
}