package com.baldomeronapoli.eventplanner.android

import android.app.Application
import android.content.Context
import com.baldomeronapoli.eventplanner.android.di.appCoreMode
import com.baldomeronapoli.eventplanner.di.DependencyInjection
import com.baldomeronapoli.eventplanner.di.sharedPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        sharedPrefs = applicationContext.getSharedPreferences(
            applicationContext.packageName,
            Context.MODE_PRIVATE
        )
        val a = DependencyInjection.initKoinAndReturnInstance {
            androidLogger()
            androidContext(this@MyApp)
        }
        //TODO: ver si se puede injectar este modulo de manera mas bonita
        a.loadModules(listOf(appCoreMode))
    }
}