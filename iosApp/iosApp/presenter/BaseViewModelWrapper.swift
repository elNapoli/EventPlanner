//
//  BaseViewModelWrapper.swift
//  EventPlanner
//
//  Created by Baldomero Aguila on 22-07-24.
//  Copyright © 2024 orgName. All rights reserved.
//
import Combine
import Foundation
import KMPNativeCoroutinesCombine
import KMPObservableViewModelSwiftUI
import shared
import SwiftUI

class BaseViewModelWrapper<STATE: BaseUiState, INTENT: BaseUiIntent, EFFECT: BaseEffect>: ObservableObject {
    @Published var state: STATE
    @Published var effect: EFFECT
    private var cancellables = Set<AnyCancellable>()
    var sendIntent: (INTENT) -> Void

    init(viewModel: BaseViewModel<STATE, INTENT, EFFECT>, initialEffect: EFFECT) {
        state = STATE()
        effect = initialEffect
        sendIntent = viewModel.sendIntent

        setupStateBinding(for: viewModel)
        setupEffectBinding(for: viewModel)
    }

    private func setupStateBinding(for viewModel: BaseViewModel<STATE, INTENT, EFFECT>) {
        let stateFlow = createPublisher(for: viewModel.uiStateFlow)

        stateFlow
            .sink(
                receiveCompletion: { _ in },
                receiveValue: { [weak self] state in
                    self?.handleState(state)
                }
            )
            .store(in: &cancellables)
    }

    private func setupEffectBinding(for viewModel: BaseViewModel<STATE, INTENT, EFFECT>) {
        let effectFlow = createPublisher(for: viewModel.effectFlow)

        effectFlow
            .sink(
                receiveCompletion: { _ in },
                receiveValue: { [weak self] effect in
                    self?.handleEffect(effect)
                }
            )
            .store(in: &cancellables)
    }

    private func handleState(_ state: Any) {
        DispatchQueue.main.async {
            self.state = state as! STATE
        }
    }

    private func handleEffect(_ effect: Any?) {
        DispatchQueue.main.async {
            if let effect = effect as? EFFECT {
                self.effect = effect
                self.processEffect(effect)
            }
        }
    }

    func processEffect(_: EFFECT) {}
}
