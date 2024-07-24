//
//  HomeView.swift
//  EventPlanner
//
//  Created by Baldomero Aguila on 21-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct HomeView: View {
    var body: some View {
        VStack {
            Text("Home Screen")
                .font(.largeTitle)
                .padding()
            NavigationLink("Sign In", value: AuthRoute.signIn)
            NavigationLink("Sign Out", value: AuthRoute.signUp)
        }
        .navigationTitle("Home")
    }
}

#Preview {
    HomeView()
}
