import KMPObservableViewModelSwiftUI
import shared
import SwiftUI

struct ContentView: View {
    @StateViewModel private var viewModel = ViewModels().onBoardViewModel()

    var body: some View {
        VStack {
            let state = viewModel.uiState as! OnboardContractUiState
            let effect = viewModel.effect as? OnboardContractEffect

            if state.showOnboarding {
                Text("Mostrar onboarding")
            } else {
                Text("No mostrar ")
            }
            Text("\(effect)")
            Button(action: {
                viewModel.handleIntent(uiIntent: OnboardContractUiIntentCompleteOnboarding())
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
