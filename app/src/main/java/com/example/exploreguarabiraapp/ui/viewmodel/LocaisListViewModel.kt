package com.example.exploreguarabiraapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exploreguarabiraapp.data.models.Categoria
import com.example.exploreguarabiraapp.data.models.Local
import com.example.exploreguarabiraapp.data.repository.LocalRepository
import com.example.exploreguarabiraapp.ui.state.LocaisListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocaisListViewModel(
    private val repository: LocalRepository,
    private val categoriaId: String
) : ViewModel() {

    private val categoria: Categoria =
        repository.getCategoriaPorId(categoriaId)

    // _uiState privado e mutável, acessível apenas dentro do ViewModel
    private val _uiState = MutableStateFlow(
        LocaisListUiState(
            categoria = categoria,
            isLoading = true)
    )

    // uiState público e imutável, exposto para a UI
    val uiState: StateFlow<LocaisListUiState> = _uiState.asStateFlow()

    init {
        carregarLocais()
    }

    private fun carregarLocais() {
        viewModelScope.launch {
            repository.getLocaisPorCategoria(categoriaId)
                .onStart {
                    _uiState.update { it.copy(isLoading = true) }
                }
                .catch {

                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            snackbarMessage = "Não foi possível carregar os locais. Verifique sua conexão."
                        )
                    }
                }
                .collect { locais ->
                    _uiState.update {
                        it.copy(
                            locais = locais,
                            locaisFiltrados = locais,
                            isLoading = false
                        )
                    }
                }
        }
    }

    // EVENTO: Chamado quando o usuário digita na barra de pesquisa.

    fun onSearchTextChange(newText: String) {

        val query = newText.trim()

        val filtrados = _uiState.value.locais.filter {
            it.nome.contains(query, true) ||
                    it.endereco.contains(query, true)
        }


        _uiState.update {
            it.copy(
                searchQuery = newText,
                locaisFiltrados = filtrados
            )
        }
    }

    // EVENTO: Chamado quando o usuário clica em um item da lista.

    fun onLocalSelected(local: Local?) {
        _uiState.update { it.copy(selectedLocal = local) }
    }

    fun onSnackbarShown() {
        _uiState.update { it.copy(snackbarMessage = null) }
    }
}