//
//  OnboardScreen.swift
//  iosApp
//
//  Created by Baldomero Aguila on 17-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct OnboardScreen: View {
    var onboardPages: [OnboardPage] = onboardPagesList
    @State private var currentPage = 1

    var body: some View {
        TabView(selection: $currentPage) {
            ForEach(Array(onboardPages.enumerated()), id: \.element.id) { _, item in
                OnboardPageView(onboardPage: item, onBoardPageSize: onboardPages.count, currentPage: $currentPage)
            } //: LOOP
        } //: TAB
        .tabViewStyle(PageTabViewStyle())
        .padding(.vertical, 20)
        .onAppear {
            UIPageControl.appearance().currentPageIndicatorTintColor = UIColor(Color.primary)
            UIPageControl.appearance().pageIndicatorTintColor = UIColor(Color.primary).withAlphaComponent(0.2)
        }
    }
}

#Preview {
    OnboardScreen()
}
