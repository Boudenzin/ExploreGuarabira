package com.example.exploreguarabiraapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.currentComposer
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
    private val categoriaInicial: Categoria
) : ViewModel() {

    // _uiState privado e mutável, acessível apenas dentro do ViewModel
    private val _uiState = MutableStateFlow(LocaisListUiState(categoria = categoriaInicial))

    // uiState público e imutável, exposto para a UI
    val uiState: StateFlow<LocaisListUiState> = _uiState.asStateFlow()

    init {
        carregarLocais()
    }

    private fun carregarLocais() {
        viewModelScope.launch {
            repository.getLocaisPorCategoria(categoriaInicial.id)
                .catch { e ->
                    Log.e("LocalViewModel", "Erro ao carregar locais", e)

                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            snackbarMessage = "Não foi possível carregar os locais. Verifique sua conexão."
                        )
                    }
                }
                .collect { locais ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            locais = locais,
                            locaisFiltrados = locais,
                            isLoading = false,
                            snackbarMessage = null
                        )
                    }
                }
        }
    }

    // EVENTO: Chamado quando o usuário digita na barra de pesquisa.

    fun onSearchTextChange(newText: String) {

        val locais = _uiState.value.locais
        val filtrados = _uiState.value.locais.filter {
            it.nome.contains(newText, ignoreCase = true) || it.endereco.contains(newText, ignoreCase = true)
        }

        _uiState.update { currentState ->
            currentState.copy(
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