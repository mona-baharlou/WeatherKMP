package com.baharlou.weatherkmp.android

import android.app.Application
import com.baharlou.weatherkmp.initializeNaier

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeNaier()
    }
}