package com.ddd.carssok.core.network.api

import com.ddd.carssok.core.network.model.AuthRequestBody
import com.ddd.carssok.core.network.model.UserTokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth")
    suspend fun requestAuth(@Body authRequest: AuthRequestBody): UserTokenResponse

    @GET("/auth")
    suspend fun getUserToken() : UserTokenResponse
}