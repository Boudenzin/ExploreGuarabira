package com.example.exploreguarabiraapp.ui.screens

import androidx.activity.compose.LocalActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exploreguarabiraapp.data.repository.LocalRepositoryInstance
import com.example.exploreguarabiraapp.utils.DeviceType
import com.example.exploreguarabiraapp.utils.deviceTypeFromWidth


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    val activity = LocalActivity.current
    val deviceType = activity?.let {
        val windowSizeClass = calculateWindowSizeClass(it)
        deviceTypeFromWidth(windowSizeClass.widthSizeClass)
    } ?: DeviceType.PHONE
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
    ) {
        composable(route = Screen.Dashboard.route) {
            DashboardScreen(
                deviceType = deviceType,
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