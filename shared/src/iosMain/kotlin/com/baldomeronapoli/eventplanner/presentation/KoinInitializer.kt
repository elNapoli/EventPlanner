package com.baldomeronapoli.eventplanner.presentation

import com.baldomeronapoli.eventplanner.di.commonModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {

    startKoin {
        modules(commonModule)
    }
}

class KoinViewModel : KoinComponent {

    val greetingViewModel: GreetingViewModel by inject()
}