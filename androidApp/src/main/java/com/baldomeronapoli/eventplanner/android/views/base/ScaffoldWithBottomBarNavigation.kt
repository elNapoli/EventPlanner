package com.baldomeronapoli.eventplanner.android.views.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalActivity
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.components.NAppBottomNavigation
import com.baldomeronapoli.eventplanner.android.models.BottomRoute
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute
import com.baldomeronapoli.eventplanner.android.views.LocalNavigationViewModel


var routes = listOf(
    BottomRoute(MainRoute.Home.path, "Home", Icons.Filled.Home),
    BottomRoute(MainRoute.Search.path, "Buscar", Icons.Filled.Search),
    BottomRoute(MainRoute.MyEvents.path, "Mis eventos", Icons.Filled.LocalActivity),
    BottomRoute(MainRoute.Profile.path, "Perfil", Icons.Filled.Person)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithBottomBarNavigation(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable () -> Unit
) {
    val navigationViewModel = LocalNavigationViewModel.current
    Scaffold(
        modifier = modifier,
        floatingActionButtonPosition = floatingActionButtonPosition,
        snackbarHost = snackbarHost,
        contentWindowInsets = contentWindowInsets,
        contentColor = contentColor,
        floatingActionButton = floatingActionButton,
        topBar = topBar,
        bottomBar = {
            NAppBottomNavigation(navigationViewModel.activityNavController, items = routes)
        }
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
    ScaffoldWithBottomBarNavigation(
        modifier = Modifier.fillMaxSize()
    ) {
        // Content for preview
        Text(text = "Hello, World!")
    }
}