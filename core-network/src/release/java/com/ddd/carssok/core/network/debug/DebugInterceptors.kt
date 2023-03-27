package com.ddd.carssok.core.network.debug

import okhttp3.Interceptor
import okhttp3.Response

object DebugInterceptors {
    val flipperInterceptor = BypassOkHttpInterceptor
}

object BypassOkHttpInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}