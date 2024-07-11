import SwiftUI
import shared


extension ContentView {
    
    @MainActor
    class GreetingViewModelWrapper: ObservableObject {
        
        @Published var state: GreetingState = GreetingState.companion.default()
    
        private let viewModel: GreetingViewModel
        private var stateSubscription: KmmSubscription!
        
        init() {
            self.viewModel = ViewModelInjector().greetingViewModel
            subscribeState()
        }
        

        
        private func subscribeState() {
            stateSubscription = viewModel.state.subscribe(
                onEach: { state in
                    self.state = state!
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
        VStack{
            Text(viewModel.state.data)
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView(viewModel: .init())
	}
}
