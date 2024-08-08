package com.baldomeronapoli.eventplanner.di

import android.content.Context
import android.content.SharedPreferences
import com.baldomero.napoli.eventplanner.core.PreferencesManager
import com.baldomero.napoli.eventplanner.onboarding.presentation.OnBoardViewModel
import com.baldomeronapoli.eventplanner.presentation.auth.AuthViewModel
import com.baldomeronapoli.eventplanner.presentation.event.EventViewModel
import com.baldomeronapoli.eventplanner.presentation.main.MainViewModel
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual fun platformModule() = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            androidContext().packageName,
            Context.MODE_PRIVATE
        )
    }
    // onboard
    viewModel { OnBoardViewModel(get()) }

    //auth
    viewModel { AuthViewModel(get(), get(), get()) }
    viewModel { EventViewModel(get(), get(), get(), get(), get(), get()) }

    //Main
    viewModel { MainViewModel(get()) }

    single { PreferencesManager(SharedPreferencesSettings(get())) }


}