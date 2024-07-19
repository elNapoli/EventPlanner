package com.baldomeronapoli.eventplanner.di

import com.baldomeronapoli.eventplanner.data.repositories.GreetingRepositoryImpl
import com.baldomeronapoli.eventplanner.domain.repositories.GreetingRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<GreetingRepository> { GreetingRepositoryImpl() }

}