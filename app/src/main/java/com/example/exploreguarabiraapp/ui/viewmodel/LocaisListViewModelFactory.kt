package com.example.exploreguarabiraapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exploreguarabiraapp.data.models.Categoria
import com.example.exploreguarabiraapp.data.repository.LocalRepository

class LocaisListViewModelFactory (
    private val repository: LocalRepository,
    private val categoria: Categoria,
) : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocaisListViewModel::class.java)) {
            return LocaisListViewModel(repository, categoria) as T
        }
        throw IllegalArgumentException("Classe de ViewModel Desconhecida (UNKNOWN)")
    }
}