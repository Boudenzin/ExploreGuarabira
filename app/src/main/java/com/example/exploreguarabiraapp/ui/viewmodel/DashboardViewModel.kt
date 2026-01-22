package com.example.exploreguarabiraapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.exploreguarabiraapp.data.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: LocalRepository
) : ViewModel() {

    val categorias = repository.getTodasCategorias()
}
