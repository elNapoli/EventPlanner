import FirebaseCore
import shared
import SwiftUI

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_: UIApplication,

                     didFinishLaunchingWithOptions _: [UIApplication.LaunchOptionsKey: Any]? = nil) -> Bool
    {
        FirebaseApp.configure()

        return true
    }
}

@main
struct iOSApp: App {
    init() {
        DependencyInjection().doInitKoin { _ in }
        UINavigationBar.configureAppearance()
        // register app delegate for Firebase setup

        @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    }

    var body: some Scene {
        WindowGroup {
            MainView()
        }
    }
}
