import shared
import SwiftUI

@main
struct iOSApp: App {
    init() {
        KoinInitializerKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView(viewModel: .init())
        }
    }
}
