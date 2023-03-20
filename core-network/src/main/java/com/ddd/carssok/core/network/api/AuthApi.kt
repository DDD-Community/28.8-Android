package com.ddd.carssok.core.network.api

import com.ddd.carssok.core.network.ApiResult
import com.ddd.carssok.core.network.model.AuthRequestBody
import com.ddd.carssok.core.network.model.BaseModel
import com.ddd.carssok.core.network.model.UserTokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth")
    suspend fun requestAuth(@Body authRequest: AuthRequestBody): ApiResult<BaseModel<UserTokenResponse>>

    @GET("/auth")
    suspend fun getUserToken(@Header("device-id") deviceId: String): ApiResult<BaseModel<UserTokenResponse>>
}