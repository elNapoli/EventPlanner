//
//  AlertSticky.swift
//  EventPlanner
//
//  Created by Baldomero Aguila on 22-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared
import SwiftUI

struct AlertSticky: View {
    var alertType: AlertType = .success
    var text: String

    private var icon: Image {
        switch alertType {
        case .success:
            return Image(systemName: "checkmark.circle")
        case .error:
            return Image(systemName: "exclamationmark.octagon")
        case .warning:
            return Image(systemName: "exclamationmark.triangle")
        default:
            return Image(systemName: "exclamationmark.triangle")
        }
    }

    private var backgroundColor: Color {
        switch alertType {
        case .success:
            return Color("NSuccess").opacity(0.2)
        case .error:
            return Color("NRed").opacity(0.2)
        case .warning:
            return Color("NWarning").opacity(0.2)
        default:
            return Color("NWarning").opacity(0.2)
        }
    }

    private var color: Color {
        switch alertType {
        case .success:
            return Color("NSuccess")
        case .error:
            return Color("NRed")
        case .warning:
            return Color("NWarning")
        default:
            return Color("NWarning")
        }
    }

    var body: some View {
        HStack(spacing: 16) {
            icon
                .foregroundColor(color)
            Text(text)
                .foregroundColor(color)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding(16)
        .background(backgroundColor)
        .cornerRadius(8)
    }
}

#Preview {
    NavigationStack {
        VStack(spacing: 16) {
            AlertSticky(alertType: .success, text: "esto es una prueba")
            AlertSticky(alertType: .error, text: "esto es una prueba")
            AlertSticky(alertType: .warning, text: "esto es una prueba")
        }
    }
}
