package com.baldomeronapoli.eventplanner.di

import com.baldomero.napoli.eventplanner.core.PreferencesManager
import com.baldomeronapoli.eventplanner.data.repositories.AuthRepositoryImpl
import com.baldomeronapoli.eventplanner.domain.repositories.AuthRepository
import com.baldomeronapoli.eventplanner.domain.usecases.auth.CreateUseWithEmailAndPasswordUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.auth.SignInWithEmailAndPasswordUseCase
import com.baldomeronapoli.eventplanner.presentation.auth.AuthViewModel
import com.baldomeronapoli.eventplanner.presentation.onBoard.OnBoardViewModel
import com.russhwolf.settings.NSUserDefaultsSettings
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults


actual fun platformModule() = module {

    // onboard
    factory { PreferencesManager(NSUserDefaultsSettings(NSUserDefaults())) }


    factory { OnBoardViewModel(get()) }

    //auth
    factory { AuthViewModel(get(), get(), get()) }
    single { CreateUseWithEmailAndPasswordUseCase(get()) }
    single { SignInWithEmailAndPasswordUseCase(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get(), get()) }
}

@Suppress("unused")
object ViewModels : KoinComponent {
    fun authViewModel() = get<AuthViewModel>()
    fun onBoardViewModel() = get<OnBoardViewModel>()
}

@Suppress("unused")
object AppDependencies : KoinComponent {
    fun preferencesManager() = get<PreferencesManager>()
}