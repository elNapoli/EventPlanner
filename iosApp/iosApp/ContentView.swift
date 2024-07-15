import SwiftUI
import shared


extension ContentView {
    @MainActor
    class GreetingViewModelWrapper: ObservableObject {
       

        private let viewModel: GreetingViewModel
        private let hola: GreetingContract.hola
        

        init() {
            viewModel = ViewModelInjector().greetingViewModel
        }


    }
}

struct ContentView: View {
    @ObservedObject private(set) var viewModel: GreetingViewModelWrapper

    var body: some View {
        VStack {
            Text("adfadsf")
        
            Button(action: {
              
            }) {
                Text("Buswcnado datos")
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(viewModel: .init())
    }
}

