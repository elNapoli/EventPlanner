package com.baldomeronapoli.eventplanner.android

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.baldomeronapoli.eventplanner.android.navigation.home.HomeRoute
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) = remember(navController) {
    AppState(
        navController = navController,
        drawerState = drawerState,
        coroutineScope = coroutineScope,
    )
}

class AppState(
    val navController: NavHostController,
    var drawerState: DrawerState,
    var coroutineScope: CoroutineScope,
) {

    @Composable
    fun getCurrentAppBarTitle(): String {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
            ?: MainRoute.Home.path

        // TODO: hay que mejorar esta parte dado que no es escalable.
        return when (currentRoute) {
            HomeRoute.Index.path -> "Inicio"
            HomeRoute.Test.path -> "Pantalla de Prueba"
            else -> "Event Planner"
        }
    }

    private fun openDrawer() {
        coroutineScope.launch {
            drawerState.open()
        }
    }

    private fun closeDrawer() {
        coroutineScope.launch {
            drawerState.close()
        }
    }

    fun toggleDrawer() {
        coroutineScope.launch {
            if (drawerState.isOpen) {
                closeDrawer()
            } else {
                openDrawer()
            }
        }
    }

}