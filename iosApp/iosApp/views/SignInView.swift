//
//  SignInView.swift
//  EventPlanner
//
//  Created by Baldomero Aguila on 21-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared
import SwiftUI

struct SignInView: View {
    var uiState: AuthContractUiState
    var effect: AuthContractEffect?
    let onIntent: (AuthContractUiIntent) -> Void

    private var emailBinding: Binding<String> {
        Binding(
            get: { uiState.email },
            set: { newValue in
                onIntent(AuthContractUiIntentSaveEmail(email: newValue))
            }
        )
    }

    private var passwordBinding: Binding<String> {
        Binding(
            get: { uiState.password },
            set: { newValue in
                onIntent(AuthContractUiIntentSavePassword(password: newValue))
            }
        )
    }

    var body: some View {
        Form {
            TextInputField("Correo electrónico", text: emailBinding)
            TextInputField("Contraseña", text: passwordBinding, isSecureField: true)

            Button(action: {
                print("asdfasdf")
                onIntent(AuthContractUiIntentSignInWithEmailAndPassword())
            }, label: {
                Text("Iniciar sesión")
            }).primary()
        }
        .navigationBarTitle("Iniciar sesión")
    }
}

extension UINavigationBar {
    static func configureAppearance() {
        let appearance = UINavigationBarAppearance()

        appearance.titleTextAttributes = [.foregroundColor: UIColor(Color.grayTitle)] // Cambia el color del título
    }
}
