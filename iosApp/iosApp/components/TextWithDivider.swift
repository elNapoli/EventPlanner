//
//  TextWithDivider.swift
//  EventPlanner
//
//  Created by Baldomero Aguila on 21-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct TextWithDivider: View {
    let text: String
    var body: some View {
        HStack {
            VStack {
                Divider()
            }
            Text(text)
                .fixedSize() // Evita que el texto se ajuste o se rompa en varias líneas
                .padding(.horizontal, 4).font(.caption)
            VStack {
                Divider()
            }
        }
        .padding(.vertical, 8) // Agrega algo de espacio vertical si es necesario
    }
}

#Preview {
    NavigationStack {
        TextWithDivider(text: "O inicia sesión usando")
    }
}
