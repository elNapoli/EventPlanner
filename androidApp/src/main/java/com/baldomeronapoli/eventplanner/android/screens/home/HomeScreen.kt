package com.baldomeronapoli.eventplanner.android.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.baldomeronapoli.eventplanner.presentation.GreetingEvent
import com.baldomeronapoli.eventplanner.presentation.GreetingState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    sendEvent: (event: GreetingEvent) -> Unit,
    state: GreetingState,
    goToTest: () -> Unit
) {


    Column {
        if (state.isLoading) {
            Text("Cargando...")
        } else {
            Text(state.data)
        }

        Button(onClick = { sendEvent(GreetingEvent.LoadData) }) {
            Text(text = "buscar datos")
        }

        Button(onClick = goToTest) {
            Text(text = "Ir a otra pantalla")
        }
    }

}