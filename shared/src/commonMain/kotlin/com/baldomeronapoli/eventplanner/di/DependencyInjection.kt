package com.baldomeronapoli.eventplanner.di

import com.baldomeronapoli.eventplanner.data.repositories.AuthRepositoryImpl
import com.baldomeronapoli.eventplanner.domain.repositories.AuthRepository
import com.baldomeronapoli.eventplanner.domain.usecases.auth.CheckIsLoggedUserUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.auth.CreateUseWithEmailAndPasswordUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.auth.SignInWithEmailAndPasswordUseCase
import com.baldomeronapoli.eventplanner.utils.SharePreferences
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect fun platformModule(): Module

@Suppress("unused")
object DependencyInjection {
    fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
        startKoin {
            appDeclaration()
            modules(appModule(), repositoryModule(), useCaseModule(), platformModule())
        }
    }

    fun initKoinAndReturnInstance(appDeclaration: KoinAppDeclaration = {}): org.koin.core.Koin =
        startKoin {
            appDeclaration()
            modules(appModule(), repositoryModule(), useCaseModule(), platformModule())
        }.koin

    private fun appModule() = module {
        single { Firebase.auth }
        single { SharePreferences() }
    }

    private fun repositoryModule() = module {
        single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    }

    private fun useCaseModule() = module {
        single { CreateUseWithEmailAndPasswordUseCase(get()) }
        single { SignInWithEmailAndPasswordUseCase(get()) }
        single { CheckIsLoggedUserUseCase(get()) }
    }
}