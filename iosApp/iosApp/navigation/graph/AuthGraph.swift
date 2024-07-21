//
//  AuthGraph.swift
//  EventPlanner
//
//  Created by Baldomero Aguila on 21-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import KMPObservableViewModelSwiftUI
import shared
import SwiftUI

struct AuthGraphView: View {
    var route: AuthRoute
    @ObservedObject var navigationViewModel: NavigationViewModel
    @StateViewModel private var viewModel = ViewModels().authViewModel()

    var body: some View {
        let state = viewModel.uiState as! AuthContractUiState
        let effect = viewModel.effect as? AuthContractEffect
        Group {
            switch route {
            case .signIn:
                SignInView(uiState: state, effect: effect, onIntent: { intent in
                    viewModel.handleIntent(uiIntent: intent)

                }).navigationTitle("Sign In")
            case .signUp:
                SignUpView().navigationTitle("Sign Up")
            case .validateEmail:
                ValidateEmailView().navigationTitle("Validate Email")
            case .successfulSignUp:
                SuccessfulSignUpView {
                    navigationViewModel.navigateTo(AuthRoute.signIn)
                }
            }
        }
    }
}
