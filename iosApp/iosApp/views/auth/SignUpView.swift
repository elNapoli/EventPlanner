//
//  SignUpView.swift
//  EventPlanner
//
//  Created by Baldomero Aguila on 21-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//
import Combine
import KMPObservableViewModelCore
import shared
import SwiftUI

struct SignUpView: View {
    var body: some View {
        ZStack(alignment: .top) {
            Color("NWhite").ignoresSafeArea()
            VStack {
                Button(action: {}) {
                    Text("Regístrate con Gmail")
                }
                Button(action: {}) {
                    Text("Regístrate con facebook")
                }
                TextWithDivider(text: "o")
                // TextInputField("Correo electrónico", text: uiState., isSecureField: false)
                // TextInputField("Contraseña", text: emailBinding, isSecureField: true)
                // TextInputField("Reingrese la contraseña", text: emailBinding, isSecureField: true)
                Button(action: {}) {
                    Text("Crear cuenta")

                }.primary()
            }.padding()
        }.navigationTitle("Registro")
    }
}

/* NavigationStack {
     // Crear un publisher simulado que emite un valor de prueba
     let effect = Just<BaseEffect?>(nil)
         .setFailureType(to: Error) // Ajusta el tipo de error a `Never` para el preview
         .eraseToAnyPublisher()
     SignUpView(
         uiState: AuthContractUiState.companion.initialUiState(), effect: effect, onIntent: { i in
             print(i)
         }
     )
 }*/
