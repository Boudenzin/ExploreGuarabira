package com.example.exploreguarabiraapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exploreguarabiraapp.data.repository.LocalRepositoryInstance


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route
        ) {
            composable(Screen.Dashboard.route) {
                DashboardScreen(
                    repository = LocalRepositoryInstance,
                    onCategorySelected = { categoria ->
                        navController.navigate(
                            Screen.LocalList.createRoute(categoria.id)
                        )
                    }
                )
            }

            composable(Screen.LocalList.route) { backStackEntry ->
                val categoriaId = backStackEntry.arguments
                    ?.getString("categoriaId") ?: return@composable

                LocaisListScreen(
                    categoriaId = categoriaId,
                    navController = navController
                )
            }
        }
    }
}