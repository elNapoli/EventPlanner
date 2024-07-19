package com.baldomeronapoli.eventplanner.di

import com.baldomeronapoli.eventplanner.utils.PlatformDependencies
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect fun platformModule(): Module

@Suppress("unused")
object DependencyInjection {
    fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
        startKoin {
            appDeclaration()
            modules(commonModule(), platformModule())
        }
    }

    fun initKoinAndReturnInstance(appDeclaration: KoinAppDeclaration = {}): org.koin.core.Koin =
        startKoin {
            appDeclaration()
            modules(commonModule(), platformModule())
        }.koin

    private fun commonModule() = module {
        single { PlatformDependencies().getSettings() }
    }
}