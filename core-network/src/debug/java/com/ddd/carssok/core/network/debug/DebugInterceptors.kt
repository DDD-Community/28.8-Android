package com.ddd.carssok.core.network.debug

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor

object DebugInterceptors {
    val flipperInterceptor = FlipperOkhttpInterceptor(DebugNetworkLoggers.networkFlipperPlugin, true)
}