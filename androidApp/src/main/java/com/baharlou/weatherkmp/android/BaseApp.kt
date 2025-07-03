package com.baharlou.weatherkmp.android

import android.app.Application
import com.baharlou.weatherkmp.initializeNaier
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeNaier()

        startKoin{
            androidContext(this@BaseApp)
            modules(coreModule, repositoryModule, viewModelModule, usecaseModule)
        }
    }
}