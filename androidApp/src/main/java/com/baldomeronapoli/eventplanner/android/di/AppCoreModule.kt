package com.baldomeronapoli.eventplanner.android.di

import com.baldomeronapoli.eventplanner.android.navigation.NavigationViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val appCoreMode = module {
    factoryOf(::NavigationViewModel)
}