//
//  ValidateEmailView.swift
//  EventPlanner
//
//  Created by Baldomero Aguila on 21-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ValidateEmailView: View {
    var body: some View {
        VStack {
            Text("Validate Email Screen")
                .font(.largeTitle)
                .padding()
                .navigationTitle("Validate Email")
            NavigationLink("ir a validacion exitosa ", value: AuthRoute.successfulSignUp)
        }
    }
}

#Preview {
    ValidateEmailView()
}
