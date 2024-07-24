//
//  SignInView.swift
//  EventPlanner
//
//  Created by Baldomero Aguila on 21-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import KMPNativeCoroutinesCombine
import shared
import SwiftUI

struct SignInView: View {
    @StateObject private var viewModel: AuthViewModelWrapper

    init(viewModel: AuthViewModelWrapper) {
        _viewModel = StateObject(wrappedValue: viewModel)
    }

    var body: some View {
        ZStack(alignment: .top) {
            Color("NWhite").ignoresSafeArea()

            VStack(alignment: .leading) {
                Text("Por favor, inicia sesión para continuar utilizando esta aplicación.").font(.footnote)
                if let dialog = viewModel.dialog {
                    AlertSticky(alertType: dialog.type, text: dialog.message)
                        .transition(.opacity)
                        .animation(.easeInOut, value: dialog)
                }

                TextInputField(title: "Correo electrónico", text: $viewModel.state.email, isSecureField: false)
                TextInputField(title: "Contraseña", text: $viewModel.state.password, isSecureField: true)

                HStack {
                    Spacer() // Esto empuja el texto hacia la derecha
                    Text("¿Olvidaste tu contraseña?")
                        .font(.callout)
                        .foregroundColor(Color("NPurple"))
                        .underline()
                        .onTapGesture(perform: {})
                }
                .padding(.top, 16)

                Spacer()
                Button(action: {
                    viewModel.sendIntent(AuthContractUiIntentSignInWithEmailAndPassword())
                }, label: {
                    Text("Iniciar sesión")
                }).primary().frame(maxWidth: .infinity).padding(.top, 72)

                TextWithDivider(text: "O inicia sesión usando")
                VStack(alignment: .center) {
                    HStack(spacing: 16) {
                        ButtonSocialNetwork(imageName: "wind.snow", action: {})
                        ButtonSocialNetwork(imageName: "wind.snow", action: {})
                    }.padding(.vertical, 32)
                    Text("¿No tienes una cuenta? Regístrate aquí")
                        .font(.callout)
                }.frame(maxWidth: .infinity)
            }.padding()
        }
    }
}

#Preview {
    NavigationStack {
        SignInView(viewModel: AuthViewModelWrapper(viewModel: ViewModels().authViewModel()))
    }
}
