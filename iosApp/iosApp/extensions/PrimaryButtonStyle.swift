//
//  PrimaryButtonStyle.swift
//  iosApp
//
//  Created by Baldomero Aguila on 17-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct PrimaryButtonStyle: ViewModifier {
    func body(content: Content) -> some View {
        content
            .padding(.horizontal, 32)
            .padding(.vertical, 14)
            .background(Color.primary)
            .cornerRadius(8)
            .accentColor(Color.white)
    }
}
