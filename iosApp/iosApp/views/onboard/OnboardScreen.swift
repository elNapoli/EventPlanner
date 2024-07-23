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
    let goToAuth: () -> Void

    var body: some View {
        TabView(selection: $currentPage) {
            ForEach(Array(onboardPages.enumerated()), id: \.element.id) { _, item in
                OnboardPageView(onboardPage: item, onBoardPageSize: onboardPages.count, currentPage: $currentPage)
            } //: LOOP
        } //: TAB
        .tabViewStyle(PageTabViewStyle())
        .padding(.vertical, 20)
        .onAppear {
            UIPageControl.appearance().currentPageIndicatorTintColor = UIColor(Color("NOrange"))
            UIPageControl.appearance().pageIndicatorTintColor = UIColor(Color("NOrange")).withAlphaComponent(0.2)
        }

        // BUTTON: START
        StartButtonView(currentPage: $currentPage, onClick: goToAuth, noOfPages: 3)
    }
}
