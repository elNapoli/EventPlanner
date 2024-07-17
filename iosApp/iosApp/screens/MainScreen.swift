//
//  MainScreen.swift
//  iosApp
//
//  Created by Baldomero Aguila on 16-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct MainScreen: View {
    var body: some View {
        TabView {
            Text("Favourites Screen")
                .tabItem {
                    Image(systemName: "heart.fill")
                    Text("Favourites")
                }
            Text("Friends Screen")
                .tabItem {
                    Image(systemName: "person.fill")
                    Text("Friends")
                }
            Text("Nearby Screen")
                .tabItem {
                    Image(systemName: "mappin.circle.fill")
                    Text("Nearby")
                }
        }
    }
}

#Preview {
    MainScreen()
}
