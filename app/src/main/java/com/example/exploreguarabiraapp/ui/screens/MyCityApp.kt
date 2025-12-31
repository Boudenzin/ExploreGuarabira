package com.example.exploreguarabiraapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.exploreguarabiraapp.data.repository.LocalRepository
import com.example.exploreguarabiraapp.data.repository.LocalRepositoryInstance

@Composable
fun MyCityApp() {
    var currentScreen by remember { mutableStateOf<Screen> (Screen.Dashboard) }

    when (val screen = currentScreen) {
        is Screen.Dashboard -> {
            DashboardScreen(
                repository = LocalRepositoryInstance,
                onCategorySelected = { categoria ->
                    currentScreen = Screen.LocalList(categoria)
                }
            )
        }
        is Screen.LocalList -> {
            LocaisListScreen(
                categoria = screen.categoria
                //TODO: IMPLEMENTAR O NAVCONTROLLER AQUI
            )
        }
    }
}