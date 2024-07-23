//
//  TextInputField.swift
//  EventPlanner
//
//  Created by Baldomero Aguila on 21-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct TextInputField: View {
    var title: String
    @Binding var text: String
    var isSecureField: Bool = false

    var body: some View {
        ZStack(alignment: .leading) {
            Text(title)
                .foregroundColor(text.isEmpty ? Color(.placeholderText) : .accentColor)
                .offset(y: text.isEmpty ? 0 : -25)
                .scaleEffect(text.isEmpty ? 1 : 0.8, anchor: .leading)
            if isSecureField {
                SecureField("", text: $text)
            } else {
                TextField("", text: $text)
            }
        }
        .padding(.top, 24)
        .animation(.default, value: text)

        Rectangle()
            .fill(Color("NGray40"))
            .frame(height: 1)
    }
}

#Preview {
    NavigationStack {
        ZStack(alignment: .top) {
            VStack {
                @State var value = "adsfasdf"
                TextInputField(title: "prueba", text: $value, isSecureField: false)
                TextInputField(title: "prueba", text: $value, isSecureField: false)
            }
        }
    }
}
