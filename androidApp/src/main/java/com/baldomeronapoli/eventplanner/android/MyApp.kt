package com.baldomeronapoli.eventplanner.android

import android.app.Application
import com.baldomeronapoli.eventplanner.android.di.appCoreMode
import com.baldomeronapoli.eventplanner.di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val modules = commonModule + appCoreMode

        startKoin {
            androidContext(this@MyApp)
            modules(modules)
        }
    }
}