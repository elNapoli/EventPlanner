package com.baldomeronapoli.eventplanner.di

import com.baldomeronapoli.eventplanner.presentation.GreetingViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val viewModelModule = module {
    factoryOf(::GreetingViewModel)
}