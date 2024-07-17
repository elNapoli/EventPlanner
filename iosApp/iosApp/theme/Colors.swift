//
//  Colors.swift
//  iosApp
//
//  Created by Baldomero Aguila on 17-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

extension UIColor {
    convenience init(_ color: Color) {
        let components = color.cgColor?.components ?? [0, 0, 0, 0]
        let red = components[0]
        let green = components[1]
        let blue = components[2]
        let alpha = components[3]
        self.init(red: red, green: green, blue: blue, alpha: alpha)
    }
}

extension Color {
    init(hex: UInt32) {
        let red = Double((hex >> 16) & 0xFF) / 255.0
        let green = Double((hex >> 8) & 0xFF) / 255.0
        let blue = Double(hex & 0xFF) / 255.0
        self.init(red: red, green: green, blue: blue)
    }
}

extension Color {
    static let primary = Color(hex: 0xFFFC_7B32)
    static let lightOrange = Color(hex: 0xFFFD_955B)
    static let navy = Color(hex: 0xFF1A_2541)
    static let purple = Color(hex: 0xFFB4_54E1)
    static let lightPurple = Color(hex: 0xFFBD_73F8)
    static let blue = Color(hex: 0xFF35_6CC2)
    static let red = Color(hex: 0x2196F3)
    static let white = Color(hex: 0xFFFF_FFFF)
    static let gray = Color(hex: 0xFFE8_E9EC)
    static let gray20 = Color(hex: 0xFFD1_D3D9)
    static let gray40 = Color(hex: 0xFFBA_BEC6)
    static let gray60 = Color(hex: 0xFFA3_A8B3)
    static let gray80 = Color(hex: 0xFF8D_92A0)
    static let gray100 = Color(hex: 0xFF76_7C8D)
    static let grayTitle = Color(hex: 0xFF43_4C62)
}
