//
//  SuccessfulSignUpView.swift
//  EventPlanner
//
//  Created by Baldomero Aguila on 21-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct SuccessfulSignUpView: View {
    let goToHome: () -> Void
    var body: some View {
        VStack {
            Button("Go to Home borra la navegacion blalbalblalb") {
                goToHome()
            }
            Text("Successful Sign Up Screen")
                .font(.largeTitle)
                .padding()
                .navigationTitle("Successful Sign Up")
        }
    }
}

#Preview {
    SuccessfulSignUpView {}
}
