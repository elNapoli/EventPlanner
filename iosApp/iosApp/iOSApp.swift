import shared
import SwiftUI

@main
struct iOSApp: App {
    init() {
        DependencyInjection().doInitKoin { _ in }
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
