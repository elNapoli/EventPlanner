//
//  MainRoute.swift
//  EventPlanner
//
//  Created by Baldomero Aguila on 20-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

protocol Route: Hashable {}

enum MainRoute: Route {
    case onBoard
    case home
}

enum AuthRoute: Route {
    case signIn
    case signUp
    case validateEmail
    case successfulSignUp
}
