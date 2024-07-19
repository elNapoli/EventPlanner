package com.baldomeronapoli.eventplanner.di

import org.koin.dsl.module

actual fun platformModule() = module {
    repositoryModule
    useCaseModule
    viewModelModule
}