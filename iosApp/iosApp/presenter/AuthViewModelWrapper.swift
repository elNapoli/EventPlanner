//
//  AuthViewModelWrapper.swift
//  EventPlanner
//
//  Created by Baldomero Aguila on 22-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import Foundation
import KMPNativeCoroutinesCombine
import KMPObservableViewModelSwiftUI
import shared
import SwiftUI

class AuthViewModelWrapper: BaseViewModelWrapper<AuthContractUiState, AuthContractUiIntent, AuthContractEffect> {
    @Published var dialog: ErrorDialog?

    init(viewModel: AuthViewModel) {
        dialog = nil
        super.init(viewModel: viewModel, initialEffect: AuthContractEffectNone())
    }

    override func processEffect(_ effect: AuthContractEffect) {
        switch effect {
        case let effect as AuthContractEffectShowAlert:
            dialog = effect.errorDialog
        default:
            print("Efecto desconocido")
        }
    }
}
