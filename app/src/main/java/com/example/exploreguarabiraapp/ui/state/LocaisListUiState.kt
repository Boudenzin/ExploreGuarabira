package com.example.exploreguarabiraapp.ui.state

import com.example.exploreguarabiraapp.data.Local

data class LocaisListUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val searchQuery: String = "",
    val locais: List<Local> = emptyList(),
    val locaisFiltrados: List<Local> = emptyList()
)

