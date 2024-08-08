package com.baldomeronapoli.eventplanner.di

import com.baldomero.napoli.eventplanner.core.PreferencesManager
import com.baldomero.napoli.eventplanner.onboarding.presentation.OnBoardViewModel
import com.baldomeronapoli.eventplanner.domain.usecases.auth.CreateUseWithEmailAndPasswordUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.auth.SignInWithEmailAndPasswordUseCase
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
    single { CreateUseWithEmailAndPasswordUseCase(get()) }
    single { SignInWithEmailAndPasswordUseCase(get()) }
}

@Suppress("unused")
object ViewModels : KoinComponent {
    fun onBoardViewModel() = get<OnBoardViewModel>()
}

@Suppress("unused")
object AppDependencies : KoinComponent {
    fun preferencesManager() = get<PreferencesManager>()
}