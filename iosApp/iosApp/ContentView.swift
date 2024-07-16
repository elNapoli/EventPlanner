import SwiftUI
import shared


extension ContentView {
    @MainActor
    class GreetingViewModelWrapper: ObservableObject {
       
        @Published var uiState =  GreetingContractUiState.companion.initialUiState()
        private let viewModel: GreetingViewModel
        private var stateSubscription: KmmSubscription!
        

        init() {
            viewModel = ViewModelInjector().greetingViewModel
            subscribeState()
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
        

    }
}

struct ContentView: View {
    @ObservedObject private(set) var viewModel: GreetingViewModelWrapper

    var body: some View {
          VStack {
              if viewModel.uiState.isLoading{
                  Text("Cargando......")
              }
              else{
                  Text(viewModel.uiState.data)
              }
          
              Button(action: {
                  viewModel.sendEvent(event: GreetingContractUiActionLoadGreeting())
              }) {
                  Text("Load Data")
              }
          }
      }}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(viewModel: .init())
    }
}

