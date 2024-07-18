package com.baldomeronapoli.eventplanner.android.views.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.baldomeronapoli.eventplanner.android.components.CollectSideEffect
import com.baldomeronapoli.eventplanner.android.components.NButton
import com.baldomeronapoli.eventplanner.android.components.NOutlinedTextField
import com.baldomeronapoli.eventplanner.presentation.GreetingContract.Effect
import com.baldomeronapoli.eventplanner.presentation.GreetingContract.UiIntent
import com.baldomeronapoli.eventplanner.presentation.GreetingContract.UiState
import com.baldomeronapoli.eventplanner.presentation.GreetingViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    sideEffect: Flow<Effect>,
    onAction: (UiIntent) -> Unit,
    goToTest: () -> Unit
) {
    CollectSideEffect(sideEffect) {
        when (it) {
            Effect.ShowCountCanNotBeNegativeToast -> {
                Log.e("TAG", "HomeScreen: ")
            }
        }
    }
    Column {
        if (uiState.isLoading) {
            Text("Cargando...")
        } else {
            Text(uiState.data)
        }
        NOutlinedTextField(value = "Hola ", onValueChange = {})

        NButton(
            enabled = true,
            onClick = { onAction(UiIntent.LoadGreeting) },
            text = "Buscar datos"
        )

        Button(onClick = goToTest) {
            Text(text = "Ir a otra pantalla")
        }
    }


}


@Preview
@Composable
fun PreviewHomeScreen() {
    val viewmodel: GreetingViewModel = koinViewModel()
    val uiState by viewmodel.uiState.collectAsState()
    HomeScreen(
        uiState = uiState,
        sideEffect = viewmodel.sideEffect,
        onAction = viewmodel::handleIntent
    ) {}
}