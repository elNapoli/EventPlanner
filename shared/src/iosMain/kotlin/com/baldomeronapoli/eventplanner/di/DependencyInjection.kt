package com.baldomeronapoli.eventplanner.di

import com.baldomeronapoli.eventplanner.presentation.GreetingViewModel
import com.baldomeronapoli.eventplanner.presentation.auth.AuthViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module


actual fun platformModule() = module {
    repositoryModule
    useCaseModule
    viewModelModule
}

@Suppress("unused")
object ViewModels : KoinComponent {
    fun greetingViewModel() = get<GreetingViewModel>()
    fun authViewModel() = get<AuthViewModel>()
}