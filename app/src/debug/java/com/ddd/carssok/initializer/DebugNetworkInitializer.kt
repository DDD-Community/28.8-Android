package com.ddd.carssok.initializer

import android.content.Context
import androidx.startup.Initializer
import com.ddd.carssok.core.network.debug.DebugComponentImpl

class DebugFlipperInitializer: Initializer<Unit> {

    override fun create(context: Context) {
        DebugComponentImpl().init(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}