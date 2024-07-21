//
//  OnboardPageView.swift
//  iosApp
//
//  Created by Baldomero Aguila on 17-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct OnboardPageView: View {
    var onboardPage: OnboardPage
    var onBoardPageSize: Int
    @Binding var currentPage: Int

    @State private var isAnimating: Bool = false

    var body: some View {
        ZStack {
            VStack(spacing: 20) {
                Image(onboardPage.imageRes)
                    .resizable()
                    .scaledToFit()
                    .scaleEffect(isAnimating ? 1.0 : 0.6)

                Text(onboardPage.title)
                    .foregroundColor(Color.grayTitle)
                    .font(.title2)
                    .fontWeight(.bold)
                    .multilineTextAlignment(.center)

                Text(onboardPage.description)
                    .foregroundColor(Color.gray60)
                    .font(.subheadline)
                    .multilineTextAlignment(.center)
                    .padding(.horizontal, 16)
                    .frame(maxWidth: 480)

            }.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
        } //: ZSTACK
        .onAppear {
            withAnimation(.easeOut(duration: 0.5)) {
                isAnimating = true
            }
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .center)
        .cornerRadius(20)
        .padding(.horizontal, 20)
    }
}
