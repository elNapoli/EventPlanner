package com.baldomeronapoli.eventplanner.utils

import com.baldomeronapoli.eventplanner.data.repositories.GreetingRepositoryImpl
import com.baldomeronapoli.eventplanner.domain.repositories.GreetingRepository
import com.baldomeronapoli.eventplanner.domain.usecases.GetGreetingUseCase
import com.baldomeronapoli.eventplanner.presentation.GreetingViewModel
import com.baldomeronapoli.eventplanner.presentation.auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual fun platformModule() = module {
    viewModel { GreetingViewModel(get()) }
    viewModel { AuthViewModel() }

    single<GreetingRepository> { GreetingRepositoryImpl() }
    single { GetGreetingUseCase(get()) }
}