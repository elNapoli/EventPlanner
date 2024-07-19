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

            Button(action: {
                viewModel.handleIntent(uiIntent: GreetingContractUiIntentLoadGreeting())
            }) {
                Text("Load Data")
            }
        }
    }

    // Función para manejar los efectos
    private func handleEffect(_ effect: GreetingContractEffect) {
        // Maneja el efecto aquí
        // Por ejemplo, podrías usar un `alert` para mostrar un mensaje
        print("Efecto recibido: \(effect)")
        // Puedes agregar lógica para manejar diferentes tipos de efectos aquí
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
