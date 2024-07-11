import SwiftUI
import shared


extension ContentView {
    
    @MainActor
    class GreetingViewModelWrapper: ObservableObject {
        
        let greetingViewModel: GreetingViewModel
        
        init() {
            greetingViewModel = GreetingInjector().greetingViewModel
            greetValue = greetingViewModel.greet.value
        }
        
        @Published var greetValue: String
        
        func startObserving() {
            Task {
                for await value in greetingViewModel.greet {
                    self.greetValue = value
                }
                self.greetValue = greetingViewModel.greet.value
            }
        }
    }
}

struct ContentView: View {
    @ObservedObject private(set) var viewModel: GreetingViewModelWrapper

	var body: some View {
        VStack{
            Text(viewModel.greetValue)
        }.onAppear{
            self.viewModel.startObserving()
        }
  
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView(viewModel: .init())
	}
}
