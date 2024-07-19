import KMPObservableViewModelSwiftUI
import shared
import SwiftUI

struct ContentView: View {
    @StateViewModel private var viewModel = ViewModels().greetingViewModel()

    var body: some View {
        VStack {
            let state = viewModel.uiState as! GreetingContractUiState
            let effect = viewModel.effect as? GreetingContractEffect
            if state.isLoading {
                Text("Cargando.....")
            } else {
                Text(state.data)
            }
            Text("\(effect)")
            Button(action: {
                viewModel.handleIntent(uiIntent: GreetingContractUiIntentLoadGreeting())
            }) {
                Text("Load Data")
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
