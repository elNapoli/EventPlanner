import shared
import SwiftUI

extension ContentView {
    @MainActor
    class GreetingViewModelWrapper: ObservableObject {
        @Published var uiState = GreetingContractUiState.companion.initialUiState()
        @Published var effect: GreetingContractEffect?
        private let viewModel: GreetingViewModel

        init() {
            viewModel = KoinViewModel().greetingViewModel
        }

        func handleIntent(intent: GreetingContractUiIntent) {
            viewModel.handleIntent(uiIntent: intent)
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
                viewModel.handleIntent(intent: GreetingContractUiIntentLoadGreeting())
            }) {
                Text("Load Data")
            }
        }
    }

    static func handleSideEffect(_ sideEffect: GreetingContractEffect) {
        switch sideEffect {
        case is GreetingContractEffectShowCountCanNotBeNegativeToast:
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
