package com.baldomeronapoli.eventplanner.android.views.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.baldomeronapoli.eventplanner.android.components.NButton
import com.baldomeronapoli.eventplanner.android.views.base.ScaffoldWithToolbarAndNavigationBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    goToTest: () -> Unit
) {
    ScaffoldWithToolbarAndNavigationBar {

        Column {
            Text("hola, estoy en home")
            NButton(
                enabled = true,
                onClick = { },
                text = "Buscar datos"
            )

            Button(onClick = goToTest) {
                Text(text = "Ir a otra pantalla")
            }
        }
    }


}


@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(){}
}