package com.baldomeronapoli.eventplanner.android.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.baldomeronapoli.eventplanner.presentation.GreetingContract
import com.baldomeronapoli.eventplanner.presentation.GreetingViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: GreetingContract.UiState,
    sideEffect: Flow<GreetingContract.SideEffect>,
    onAction: (GreetingContract.UiAction) -> Unit,
    goToTest: () -> Unit
) {


    Column {
        if (uiState.isLoading) {
            Text("Cargando...")
        } else {
            Text(uiState.data)
        }

        Button(onClick = { onAction(GreetingContract.UiAction.LoadGreeting) }) {
            Text(text = "buscar datos")
        }

        Button(onClick = goToTest) {
            Text(text = "Ir a otra pantalla")
        }
    }

}

@Composable
fun PreviewHomeScreen() {
    val viewmodel: GreetingViewModel = koinViewModel()
    val uiState by viewmodel.uiState.collectAsState()
    HomeScreen(
        uiState = uiState,
        sideEffect = viewmodel.sideEffect,
        onAction = viewmodel::onAction
    ) {}
}