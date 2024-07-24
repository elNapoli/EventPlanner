package com.baldomeronapoli.eventplanner.android.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.baldomeronapoli.eventplanner.android.models.BottomRoute

@Composable
fun NAppBottomNavigation(
    navController: NavHostController,
    items: List<BottomRoute>
) {
    BottomAppBar {
        NavigationBar {
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            val currentDestination = navBackStackEntry?.destination
            items.forEach { screen ->
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(imageVector = screen.icon, contentDescription = screen.label)
                    },
                    label = { Text(screen.label) })
            }

        }
    }
}