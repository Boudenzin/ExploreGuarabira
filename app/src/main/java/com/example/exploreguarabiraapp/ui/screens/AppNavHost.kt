package com.example.exploreguarabiraapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exploreguarabiraapp.data.repository.LocalRepositoryInstance


@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
    ) {
        composable(route = Screen.Dashboard.route) {
            DashboardScreen(
                repository = LocalRepositoryInstance,
                onCategorySelected = { categoria ->
                    navController.navigate(
                        Screen.LocalList.createRoute(categoria.id)
                    )
                }
            )
        }

        composable(route = Screen.LocalList.route) { backStackEntry: NavBackStackEntry ->
            val categoriaId = backStackEntry.arguments?.getString("categoriaId") ?: return@composable

            LocaisListScreen(
                categoriaId = categoriaId,
                navController = navController
            )
        }
    }
}