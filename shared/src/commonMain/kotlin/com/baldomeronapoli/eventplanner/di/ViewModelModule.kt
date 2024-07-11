package com.baldomeronapoli.eventplanner.di

import com.baldomeronapoli.eventplanner.presentation.GreetingViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single<GreetingViewModel> { GreetingViewModel(get()) }
}