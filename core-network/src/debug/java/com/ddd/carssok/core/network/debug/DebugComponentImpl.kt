package com.ddd.carssok.core.network.debug

import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader

class DebugComponentImpl: DebugComponent {

    override fun init(context: Context) {
        SoLoader.init(context, false)

        if (FlipperUtils.shouldEnableFlipper(context)) {
            AndroidFlipperClient.getInstance(context).apply {
                addPlugin(InspectorFlipperPlugin(context, DescriptorMapping.withDefaults()))
                addPlugin(NavigationFlipperPlugin.getInstance())
                addPlugin(DatabasesFlipperPlugin(context))
                addPlugin(SharedPreferencesFlipperPlugin(context))
                addPlugin(CrashReporterPlugin.getInstance())
                addPlugin(DebugNetworkLoggers.networkFlipperPlugin)
            }.run {
                start()
            }
        }
    }
}
