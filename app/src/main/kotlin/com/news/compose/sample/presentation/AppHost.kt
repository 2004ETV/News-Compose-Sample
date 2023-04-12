package com.news.compose.sample.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.news.compose.sample.navigation.BottomBarDestination
import com.ramcosta.composedestinations.navigation.navigate

@Composable
fun AppHost() {
    val navController = rememberNavController()
    val visibleEntries by navController.visibleEntries.collectAsState()
    val isBottomNavigationBarVisible = visibleEntries.any { entry ->
        BottomBarDestination.values().any { bottomItem ->
            bottomItem.direction.startRoute.route == entry.destination.route
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomNavigationBarVisible) {
                val currentSelectedItem by navController.currentBottomItemToState()
                BottomNavigationBar(
                    selectedNavigation = currentSelectedItem,
                    onNavigationSelected = { selected ->
                        navController.navigate(selected) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                        }
                    },
                )
            }
        }
    ) { paddingValues ->
        AppNavigation(
            modifier = Modifier.padding(paddingValues),
            navController = navController
        )
    }
}