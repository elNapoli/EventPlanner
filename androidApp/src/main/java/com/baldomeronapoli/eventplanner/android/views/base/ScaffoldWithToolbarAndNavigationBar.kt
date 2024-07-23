package com.baldomeronapoli.eventplanner.android.views.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalActivity
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.components.ProfilePicture
import com.baldomeronapoli.eventplanner.android.models.BottomRoute
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute


var routes = listOf(
    BottomRoute(MainRoute.Home.path, "Home", Icons.Filled.Home),
    BottomRoute(MainRoute.Search.path, "Buscar", Icons.Filled.Search),
    BottomRoute(MainRoute.MyEvent.path, "Mis eventos", Icons.Filled.LocalActivity),
    BottomRoute(MainRoute.Profile.path, "Perfil", Icons.Filled.Person)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithToolbarAndNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    //val navigationViewModel = LocalNavigationViewModel.current
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(

                title = {
                  Row(
                      verticalAlignment = Alignment.CenterVertically,
                      horizontalArrangement = Arrangement.spacedBy(8.dp)

                  ) {
                      ProfilePicture(
                          letter = "A",
                          modifier = Modifier
                              .size(40.dp)
                      )
                      Text("Baldomero")
                  }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = null
                        )
                    }
                },

                )

        },
        bottomBar = {
            // NAppBottomNavigation(navigationViewModel.activityNavController, items = routes)
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                content()
            }
        }
    }
}


@Preview
@Composable
fun PreviewScaffoldWithToolbarAndNavigationBar(modifier: Modifier = Modifier) {
    ScaffoldWithToolbarAndNavigationBar(
        modifier = Modifier.fillMaxSize()
    ) {
        // Content for preview
        Text(text = "Hello, World!")
    }
}