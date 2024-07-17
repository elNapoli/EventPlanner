import shared
import SwiftUI

extension ContentView {
    @MainActor
    class GreetingViewModelWrapper: ObservableObject {
        @Published var uiState = GreetingContractUiState.companion.initialUiState()
        @Published var sideEffect: GreetingContractSideEffect?
        private let viewModel: GreetingViewModel
        private var stateSubscription: KmmSubscription!
        private var sideEffectSubscription: KmmSubscription!

        init() {
            viewModel = ViewModelInjector().greetingViewModel
            subscribeState()
            subscribeSideEffect()
        }

        func sendEvent(event: GreetingContractUiAction) {
            viewModel.onAction(uiAction: event)
        }

        private func subscribeState() {
            stateSubscription = viewModel.uiState.subscribe(
                onEach: { state in
                    self.uiState = state!
                },
                onCompletion: { error in
                    if let error = error {
                        print(error)
                    }
                }
            )
        }

        private func subscribeSideEffect() {
            sideEffectSubscription = viewModel.sideEffect.subscribe(
                onEach: { effect in
                    if let effect = effect {
                        // Llamar al método handleSideEffect en ContentView para manejar el side effect
                        ContentView.handleSideEffect(effect)
                    }
                },
                onCompletion: { error in
                    if let error = error {
                        print(error)
                    }
                }
            )
        }
    }
}

struct ContentView: View {
    @ObservedObject private(set) var viewModel: GreetingViewModelWrapper

    var body: some View {
        VStack {
            if viewModel.uiState.isLoading {
                Text("Cargando...")
            } else {
                Text(viewModel.uiState.data)
            }

            Button(action: {
                viewModel.sendEvent(event: GreetingContractUiActionLoadGreeting())
            }) {
                Text("Load Data")
            }
        }
    }

    static func handleSideEffect(_ sideEffect: GreetingContractSideEffect) {
        switch sideEffect {
        case is GreetingContractSideEffectShowCountCanNotBeNegativeToast:
            print("ShowCountCanNotBeNegativeToast")
        // Aquí puedes mostrar una alerta, mensaje o tomar otra acción según el side effect
        default:
            break
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(viewModel: .init())
    }
}
