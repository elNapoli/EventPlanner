package com.baldomeronapoli.eventplanner.di

import com.baldomeronapoli.eventplanner.data.repositories.AuthRepositoryImpl
import com.baldomeronapoli.eventplanner.domain.repositories.AuthRepository
import com.baldomeronapoli.eventplanner.domain.usecases.auth.CreateUseWithEmailAndPasswordUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.auth.SignInWithEmailAndPassword
import com.baldomeronapoli.eventplanner.presentation.auth.AuthViewModel
import com.baldomeronapoli.eventplanner.presentation.onBoard.OnBoardViewModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module


actual fun platformModule() = module {
    single { Firebase.auth }

    // onboard
    factory { OnBoardViewModel(get()) }

    //auth
    factory { AuthViewModel(get(), get()) }
    single { CreateUseWithEmailAndPasswordUseCase(get()) }
    single { SignInWithEmailAndPassword(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}

@Suppress("unused")
object ViewModels : KoinComponent {
    fun authViewModel() = get<AuthViewModel>()
    fun onBoardViewModel() = get<OnBoardViewModel>()
}