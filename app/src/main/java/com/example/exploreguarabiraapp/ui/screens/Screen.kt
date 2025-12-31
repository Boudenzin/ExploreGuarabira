package com.example.exploreguarabiraapp.ui.screens

import com.example.exploreguarabiraapp.data.models.Categoria

sealed class Screen {
    object Dashboard : Screen()
    data class LocalList(val categoria: Categoria) : Screen()
}