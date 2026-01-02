package com.example.exploreguarabiraapp.ui.screens

sealed class Screen(val route: String) {

    object Dashboard : Screen("dashboard")

    object LocalList : Screen("locais_list/{categoriaId}") {
        fun createRoute(categoriaId: String) =
            "locais_list/$categoriaId"
    }
}
