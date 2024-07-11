package com.baldomeronapoli.eventplanner.android

import android.app.Application
import com.baldomeronapoli.eventplanner.di.repositoryModule
import com.baldomeronapoli.eventplanner.di.useCaseModule
import com.baldomeronapoli.eventplanner.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val modules = repositoryModule + viewModelModule + useCaseModule

        startKoin {
            androidContext(this@MyApp)
            modules(modules)
        }
    }
}