package com.baldomeronapoli.eventplanner.di

import com.baldomeronapoli.eventplanner.data.repositories.AuthRepositoryImpl
import com.baldomeronapoli.eventplanner.data.repositories.GreetingRepositoryImpl
import com.baldomeronapoli.eventplanner.domain.repositories.AuthRepository
import com.baldomeronapoli.eventplanner.domain.usecases.GetGreetingUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.auth.CreateUseWithEmailAndPasswordUseCase
import com.baldomeronapoli.eventplanner.presentation.GreetingViewModel
import com.baldomeronapoli.eventplanner.presentation.auth.AuthViewModel
import com.baldomeronapoli.eventplanner.presentation.onBoard.OnBoardViewModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual fun platformModule() = module {
    single { Firebase.auth }
    single { GreetingRepositoryImpl() }
    single { GetGreetingUseCase(get()) }
    viewModel { GreetingViewModel(get()) }
    viewModel { OnBoardViewModel(get()) }

    //auth
    viewModel { AuthViewModel(get()) }
    single { CreateUseWithEmailAndPasswordUseCase(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }

}