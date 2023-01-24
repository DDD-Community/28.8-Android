package com.ddd.carssok.core.network

import com.ddd.carssok.datastore.CarssokDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val dataStore: CarssokDataStore) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            dataStore.getUserToken().firstOrNull()
        }
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("Authorization", "Bearer ${token.orEmpty()}")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}