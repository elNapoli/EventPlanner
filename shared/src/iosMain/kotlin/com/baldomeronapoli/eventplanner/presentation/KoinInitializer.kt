package com.baldomeronapoli.eventplanner.presentation

import com.baldomeronapoli.eventplanner.di.repositoryModule
import com.baldomeronapoli.eventplanner.di.useCaseModule
import com.baldomeronapoli.eventplanner.di.viewModelModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {

    val modules = repositoryModule + viewModelModule + useCaseModule

    startKoin {
        modules(modules)
    }
}

class ViewModelInjector : KoinComponent {

    val greetingViewModel: GreetingViewModel by inject()
}