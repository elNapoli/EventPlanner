//
//  MainView.swift
//  iosApp
//
//  Created by Baldomero Aguila on 16-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//
import KMPNativeCoroutinesCombine
import KMPObservableViewModelSwiftUI
import shared
import SwiftUI

struct MainView: View {
    @StateObject private var navigationViewModel = NavigationViewModel()

    let prefs = AppDependencies().sharePreferences()

    var body: some View {
        NavigationStack(path: $navigationViewModel.path) {
            VStack {
                if prefs.getShownOnboarding() {
                    OnboardScreen {
                        prefs.setShownOnboarding()
                        navigationViewModel.navigateToRoot()
                    }
                } else {
                    SignInView(viewModel: AuthViewModelWrapper(viewModel: ViewModels().authViewModel()))
                }
            }
            .navigationDestination(for: AuthRoute.self) { route in
                switch route {
                case .signIn:
                    SignInView(viewModel: AuthViewModelWrapper(viewModel: ViewModels().authViewModel())){
                        navigationViewModel.navigateTo(<#T##route: any Route##any Route#>)
                    }
                case .signUp:
                    SignInView(viewModel: AuthViewModelWrapper(viewModel: ViewModels().authViewModel()))
                case .validateEmail:
                    ValidateEmailView()
                case .successfulSignUp:
                    SuccessfulSignUpView {
                        navigationViewModel.navigateTo(AuthRoute.signIn)
                    }
                }
            }
        }
    }
}

#Preview {
    MainView()
}
