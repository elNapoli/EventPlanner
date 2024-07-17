//
//  ViewExtensions.swift
//  iosApp
//
//  Created by Baldomero Aguila on 17-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

extension View {
    func primary() -> some View {
        modifier(PrimaryButtonStyle())
    }

    func heading() -> some View {
        modifier(Heading())
    }
}
