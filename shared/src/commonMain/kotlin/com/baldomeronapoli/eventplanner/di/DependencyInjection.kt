package com.baldomeronapoli.eventplanner.di

import com.baldomero.napoli.supabase.network.config.NetworkConfig
import com.baldomero.napoli.supabase.network.di.networkModule
import com.baldomeronapoli.eventplanner.data.postgresql.queries.EventQueries
import com.baldomeronapoli.eventplanner.data.repositories.EventRepositoryImpl
import com.baldomeronapoli.eventplanner.domain.repositories.EventRepository
import com.baldomeronapoli.eventplanner.domain.usecases.events.CreateEventUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.events.GetEventByIdUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.events.GetEventsByAttendeeUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.events.GetNearbyEventsUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.events.SearchBoardGamesUseCase
import com.baldomeronapoli.eventplanner.shared.MySecrets
import dev.jordond.compass.geolocation.Geolocator
import dev.jordond.compass.geolocation.mobile
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import com.baldomero.napoli.supabase.auth.di.DependencyInjection as AuthDependencyInjection

expect fun platformModule(): Module

@Suppress("unused")
object DependencyInjection {
    fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
        startKoin {
            appDeclaration()
            modules(
                networkModule,
                appModule(),
                repositoryModule(),
                AuthDependencyInjection.init(),
                useCaseModule(),
                platformModule(),
                managersModuleO()
            )
        }
    }

    fun initKoinAndReturnInstance(appDeclaration: KoinAppDeclaration = {}): org.koin.core.Koin =
        startKoin {
            appDeclaration()
            modules(
                networkModule,
                appModule(),
                repositoryModule(),
                AuthDependencyInjection.init(),
                useCaseModule(),
                platformModule(),
                managersModuleO()
            )
        }.koin

    private fun appModule() = module {
        single { Geolocator.mobile() }
        single<NetworkConfig> {
            object : NetworkConfig {
                override val supabaseRef: String = MySecrets.PROJECT_SUPABASE_REF
                override val supabaseKey: String = MySecrets.API_KEY_SUPABASE
                override val googleClientId: String = MySecrets.GOOGLE_CLIENT_ID
            }
        }
        single {
            EventQueries(get())
        }
    }

    private fun repositoryModule() = module {
        single {
            HttpClient {
                install(ContentNegotiation) {
                    json()
                }
                defaultRequest {
                    headers {
                        append(HttpHeaders.ContentType, ContentType.Application.Json)
                        append("X-Algolia-API-Key", MySecrets.ALGOLIA_API_KEY)
                        append("X-Algolia-Application-ID", MySecrets.ALGOLIA_APPLICATION_ID)
                    }
                }
            }
        }
        single<EventRepository> { EventRepositoryImpl(get()) }
    }

    private fun managersModuleO() = module {

    }

    private fun useCaseModule() = module {
        single { CreateEventUseCase(get()) }
        single { GetEventsByAttendeeUseCase(get()) }
        single { GetEventByIdUseCase(get()) }
        single { SearchBoardGamesUseCase(get()) }
        single { GetNearbyEventsUseCase(get()) }
    }
}