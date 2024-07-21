//
//  TextInputField.swift
//  EventPlanner
//
//  Created by Baldomero Aguila on 21-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct TextInputField: View {
    private var title: String
    @Binding private var text: String
    var isSecureField: Bool

    init(_ title: String, text: Binding<String>, isSecureField: Bool = false) {
        self.title = title
        _text = text
        self.isSecureField = isSecureField
    }

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
        .padding(.top, 15)
        .animation(.default)
    }
}
