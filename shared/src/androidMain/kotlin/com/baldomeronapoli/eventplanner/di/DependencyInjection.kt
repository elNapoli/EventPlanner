package com.baldomeronapoli.eventplanner.di

import android.content.Context
import com.baldomeronapoli.eventplanner.presentation.auth.AuthViewModel
import com.baldomeronapoli.eventplanner.presentation.event.EventViewModel
import com.baldomeronapoli.eventplanner.presentation.main.MainViewModel
import com.baldomeronapoli.eventplanner.presentation.onBoard.OnBoardViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
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

}