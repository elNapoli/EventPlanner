package com.baldomeronapoli.eventplanner.android.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
            items.forEach {
                NavigationBarItem(
                    selected = navController.currentBackStackEntryAsState().value?.destination?.route == it.route,
                    onClick = {
                        navController.navigate(it.route)
                    },
                    icon = {
                        Icon(imageVector = it.icon, contentDescription = it.label)
                    },
                    label = { Text(it.label) })
            }

        }
    }
}