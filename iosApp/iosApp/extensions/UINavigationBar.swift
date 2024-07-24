//
//  UINavigationBar.swift
//  EventPlanner
//
//  Created by Baldomero Aguila on 21-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

extension UINavigationBar {
    static func configureAppearance() {
        let appearance = UINavigationBarAppearance()

        appearance.titleTextAttributes = [.foregroundColor: UIColor(Color("NGrayTitle"))] // Cambia el color del título
    }
}
