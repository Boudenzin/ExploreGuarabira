package com.example.exploreguarabiraapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exploreguarabiraapp.data.LocalRepository
import com.example.exploreguarabiraapp.ui.state.LocaisListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class LocaisListViewModel(
    private val repository: LocalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LocaisListUiState())
    val uiState: StateFlow<LocaisListUiState> = _uiState

    fun carregarLocaisPorCategoria(categoriaId: String) {
        viewModelScope.launch {
            repository.getLocaisPorCategoria(categoriaId.toInt())
                .onStart { _uiState.value = _uiState.value.copy(isLoading = true) }
                .catch { e -> _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = e.message) }
                .collect { locais ->
                    _uiState.value = _uiState.value.copy(
                        locais = locais,
                        locaisFiltrados = locais,
                        isLoading = false
                    )
                }
        }
    }

    fun onSearchQueryChanged(query: String) {
        val filtrados = _uiState.value.locais.filter {
            it.nome.contains(query, ignoreCase = true) || it.endereco.contains(query, ignoreCase = true)
        }

        _uiState.value = _uiState.value.copy(
            searchQuery = query,
            locaisFiltrados = filtrados
        )
    }
}