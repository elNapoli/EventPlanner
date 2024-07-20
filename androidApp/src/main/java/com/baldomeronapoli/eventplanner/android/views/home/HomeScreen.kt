package com.baldomeronapoli.eventplanner.android.views.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.baldomeronapoli.eventplanner.android.components.CollectEffect
import com.baldomeronapoli.eventplanner.android.components.NButton
import com.baldomeronapoli.eventplanner.android.components.NOutlinedTextField
import com.baldomeronapoli.eventplanner.presentation.GreetingContract.Effect
import com.baldomeronapoli.eventplanner.presentation.GreetingContract.UiIntent
import com.baldomeronapoli.eventplanner.presentation.GreetingContract.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    effect: StateFlow<Effect?>,
    onIntent: (UiIntent) -> Unit,
    goToTest: () -> Unit
) {

    CollectEffect(effect) {
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
            onClick = { onIntent(UiIntent.LoadGreeting) },
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
    val effect: StateFlow<Effect> =
        MutableStateFlow(Effect.ShowCountCanNotBeNegativeToast)

    HomeScreen(
        uiState = UiState.initialUiState(),
        effect = effect,
        onIntent = { }
    ) {}
}