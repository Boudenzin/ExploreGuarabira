package com.example.exploreguarabiraapp.ui.state

import com.example.exploreguarabiraapp.data.models.Categoria
import com.example.exploreguarabiraapp.data.models.Local

data class LocaisListUiState(
    val categoria: Categoria,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val searchQuery: String = "",
    val locais: List<Local> = emptyList(),
    val locaisFiltrados: List<Local> = emptyList(),
    val selectedLocal: Local? = null,
    val snackbarMessage: String? = null
)

