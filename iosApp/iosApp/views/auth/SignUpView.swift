//
//  SignUpView.swift
//  EventPlanner
//
//  Created by Baldomero Aguila on 21-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct SignUpView: View {
    var body: some View {
        VStack {
            Text("Sign Up Screen")
                .font(.largeTitle)
                .padding()
                .navigationTitle("Sign Up")
            NavigationLink("Validate Email", value: AuthRoute.validateEmail)
        }
    }
}

#Preview {
    SignUpView()
}
