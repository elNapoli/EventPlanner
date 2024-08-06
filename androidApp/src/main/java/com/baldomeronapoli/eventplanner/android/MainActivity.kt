package com.baldomeronapoli.eventplanner.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.baldomeronapoli.eventplanner.android.views.MainScreen
import com.baldomeronapoli.eventplanner.presentation.main.MainContract
import com.baldomeronapoli.eventplanner.presentation.main.MainViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

val LocalMainViewModel = compositionLocalOf<MainViewModel> { error("MainViewModel not provided") }

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        viewModel.sendIntent(MainContract.UiIntent.CheckIsLoggedUser)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    splashScreen.setKeepOnScreenCondition { uiState.loading }
                }
            }
        }
        setContent {
            CompositionLocalProvider(LocalMainViewModel provides viewModel) {
                MainScreen()
            }

        }
    }


}
