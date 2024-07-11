package com.baldomeronapoli.eventplanner.di

import com.baldomeronapoli.eventplanner.domain.usecases.GetGreetingUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::GetGreetingUseCase)
}