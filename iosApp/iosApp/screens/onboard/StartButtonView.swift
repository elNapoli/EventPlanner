//
//  startButtonView.swift
//  iosApp
//
//  Created by Baldomero Aguila on 17-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//
import SwiftUI

struct StartButtonView: View {
    @Binding var currentPage: Int
    var noOfPages: Int
    @AppStorage("isOnboarding") var isOnboarding: Bool?

    var body: some View {
        Button(action: {
            isOnboarding = false
            currentPage += 1
        }) {
            HStack(spacing: 8) {
                if currentPage < noOfPages {
                    Text("Siguiente")
                } else {
                    Text("¡Comencemos!")
                }
            }
        }.primary()
    }
}
