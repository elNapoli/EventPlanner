//
//  MainView.swift
//  iosApp
//
//  Created by Baldomero Aguila on 16-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//
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
                    Button(action: {
                               navigationViewModel.navigateTo(AuthRoute.signIn)
                           },
                           label: {
                               /*@START_MENU_TOKEN@*/Text("Button")/*@END_MENU_TOKEN@*/
                           })
                }
            }
            .navigationDestination(for: AuthRoute.self) { route in
                // Aplicar el título en el cuerpo de cada vista
                AuthGraphView(route: route, navigationViewModel: navigationViewModel)
            }
        }
    }
}

#Preview {
    MainView()
}
