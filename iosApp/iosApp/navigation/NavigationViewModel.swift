//
//  NavigationViewModel.swift
//  EventPlanner
//
//  Created by Baldomero Aguila on 21-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI

class NavigationViewModel: ObservableObject {
    @Published var path = NavigationPath()

    func navigateTo(_ route: any Route) {
        path.append(route)
    }

    func navigateToRoot() {
        path = NavigationPath()
    }
}
