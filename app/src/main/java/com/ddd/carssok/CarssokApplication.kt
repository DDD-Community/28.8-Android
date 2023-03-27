package com.ddd.carssok

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CarssokApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}