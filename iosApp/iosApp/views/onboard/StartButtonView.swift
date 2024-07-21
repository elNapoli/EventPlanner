//
//  startButtonView.swift
//  iosApp
//
//  Created by Baldomero Aguila on 17-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//
import shared
import SwiftUI

struct StartButtonView: View {
    @Binding var currentPage: Int
    let onClick: () -> Void
    var noOfPages: Int
    var body: some View {
        Button(action: {
            if currentPage < noOfPages {
                currentPage += 1
            } else {
                onClick()
            }

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
