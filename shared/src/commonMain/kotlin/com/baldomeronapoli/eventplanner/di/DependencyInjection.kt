package com.baldomeronapoli.eventplanner.di

import com.baldomeronapoli.eventplanner.data.repositories.AuthRepositoryImpl
import com.baldomeronapoli.eventplanner.data.repositories.EventRepositoryImpl
import com.baldomeronapoli.eventplanner.domain.repositories.AuthRepository
import com.baldomeronapoli.eventplanner.domain.repositories.EventRepository
import com.baldomeronapoli.eventplanner.domain.usecases.auth.CheckIsLoggedUserUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.auth.CreateUseWithEmailAndPasswordUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.auth.SignInWithEmailAndPasswordUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.events.CreateEventUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.events.SearchBoardGamesUseCase
import com.baldomeronapoli.eventplanner.utils.SharePreferences
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.storage.storage
import dev.jordond.compass.geolocation.Geolocator
import dev.jordond.compass.geolocation.mobile
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
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
        single { Firebase.firestore }
        single { Firebase.storage }
        single { Geolocator.mobile() }
        single { SharePreferences() }
    }

    private fun repositoryModule() = module {
        single {
            HttpClient {
                install(ContentNegotiation) {
                    json()
                }
            }
        }
        single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
        single<EventRepository> { EventRepositoryImpl(get(), get(), get(), get()) }
    }

    private fun useCaseModule() = module {
        single { CreateUseWithEmailAndPasswordUseCase(get()) }
        single { SignInWithEmailAndPasswordUseCase(get()) }
        single { CheckIsLoggedUserUseCase(get()) }
        single { CreateEventUseCase(get()) }
        single { SearchBoardGamesUseCase(get()) }
    }
}