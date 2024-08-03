package com.baldomeronapoli.eventplanner.di

import com.baldomeronapoli.eventplanner.data.postgresql.queries.EventQueries
import com.baldomeronapoli.eventplanner.data.repositories.AuthRepositoryImpl
import com.baldomeronapoli.eventplanner.data.repositories.EventRepositoryImpl
import com.baldomeronapoli.eventplanner.data.services.AlgoliaService
import com.baldomeronapoli.eventplanner.domain.repositories.AuthRepository
import com.baldomeronapoli.eventplanner.domain.repositories.EventRepository
import com.baldomeronapoli.eventplanner.domain.usecases.auth.CheckIsLoggedUserUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.auth.CreateUseWithEmailAndPasswordUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.auth.LoginWithGoogleUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.auth.SignInWithEmailAndPasswordUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.events.CreateEventUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.events.GetEventByIdUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.events.GetEventsByAttendeeUseCase
import com.baldomeronapoli.eventplanner.shared.MySecrets
import com.baldomeronapoli.eventplanner.utils.SharePreferences
import dev.jordond.compass.geolocation.Geolocator
import dev.jordond.compass.geolocation.mobile
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.appleNativeLogin
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.storage.Storage
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

expect fun platformModule(): Module

@Suppress("unused")
object DependencyInjection {
    fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
        startKoin {
            appDeclaration()
            modules(
                appModule(),
                repositoryModule(),
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
                appModule(),
                repositoryModule(),
                useCaseModule(),
                platformModule(),
                managersModuleO()
            )
        }.koin

    private fun appModule() = module {
        single { Geolocator.mobile() }
        single { SharePreferences() }
        single {
            createSupabaseClient(
                supabaseUrl = "https://${MySecrets.PROJECT_SUPABASE_REF}.supabase.co",
                supabaseKey = MySecrets.API_KEY_SUPABASE
            ) {

                //...

                install(Storage) {
                    // settings
                }

                install(Postgrest) {

                }

                install(Realtime) {
                    // settings
                }

                install(Auth) {
                }
                install(ComposeAuth) {
                    googleNativeLogin(serverClientId = MySecrets.GOOGLE_CLIENT_ID)
                    appleNativeLogin()
                }

            }
        }
        single {
            EventQueries(get())
        }
    }

    private fun repositoryModule() = module {
        single { AlgoliaService(get()) }
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
        single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
        single<EventRepository> { EventRepositoryImpl(get(), get()) }
    }

    private fun managersModuleO() = module {

    }

    private fun useCaseModule() = module {
        single { CreateUseWithEmailAndPasswordUseCase(get()) }
        single { SignInWithEmailAndPasswordUseCase(get()) }
        single { CheckIsLoggedUserUseCase(get()) }
        single { CreateEventUseCase(get()) }
        single { GetEventsByAttendeeUseCase(get()) }
        single { GetEventByIdUseCase(get()) }
        single { LoginWithGoogleUseCase(get()) }
    }
}