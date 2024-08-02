package com.baldomeronapoli.eventplanner.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.baldomeronapoli.eventplanner.android.views.MainScreen
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract
import com.baldomeronapoli.eventplanner.presentation.auth.AuthViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val viewModel: AuthViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        viewModel.sendIntent(AuthContract.UiIntent.CheckIsLoggedUser)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    splashScreen.setKeepOnScreenCondition { uiState.loading }
                }
            }
        }
        setContent {
            MainScreen(
                appState = rememberAppState(),
                state = viewModel.uiState.collectAsStateWithLifecycle().value
            )
        }
    }


}
