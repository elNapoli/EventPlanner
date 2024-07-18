package com.baldomeronapoli.eventplanner.android

import android.app.Application
import android.content.Context
import com.baldomeronapoli.eventplanner.android.di.appCoreMode
import com.baldomeronapoli.eventplanner.utils.DependencyInjection
import com.baldomeronapoli.eventplanner.utils.sharedPrefsForPlatformDependencies
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        sharedPrefsForPlatformDependencies = applicationContext.getSharedPreferences(
            applicationContext.packageName,
            Context.MODE_PRIVATE
        )
        val a = DependencyInjection.initKoinAndReturnInstance {
            androidLogger()
            androidContext(this@MyApp)
        }
        a.loadModules(listOf(appCoreMode))
    }
}