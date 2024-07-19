package com.baldomeronapoli.eventplanner.di

import com.baldomeronapoli.eventplanner.data.repositories.GreetingRepositoryImpl
import com.baldomeronapoli.eventplanner.domain.repositories.GreetingRepository
import com.baldomeronapoli.eventplanner.domain.usecases.GetGreetingUseCase
import com.baldomeronapoli.eventplanner.presentation.GreetingViewModel
import com.baldomeronapoli.eventplanner.presentation.auth.AuthViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module


actual fun platformModule() = module {
    single<GreetingRepository> { GreetingRepositoryImpl() }
    single { GetGreetingUseCase(get()) }
    factory { GreetingViewModel(get()) }
    factory { AuthViewModel() }
}

@Suppress("unused")
object ViewModels : KoinComponent {
    fun greetingViewModel() = get<GreetingViewModel>()
    fun authViewModel() = get<AuthViewModel>()
}