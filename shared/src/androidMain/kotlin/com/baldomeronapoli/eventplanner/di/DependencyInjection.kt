package com.baldomeronapoli.eventplanner.di

import android.content.Context
import android.content.SharedPreferences
import com.baldomero.napoli.eventplanner.core.PreferencesManager
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun platformModule() = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            androidContext().packageName,
            Context.MODE_PRIVATE
        )
    }

    //Main

    single { PreferencesManager(SharedPreferencesSettings(get())) }


}