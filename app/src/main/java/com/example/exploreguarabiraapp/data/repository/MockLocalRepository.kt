package com.example.exploreguarabiraapp.data.repository

import com.example.exploreguarabiraapp.data.datasource.mockCategorias
import com.example.exploreguarabiraapp.data.datasource.mockLocais
import com.example.exploreguarabiraapp.data.models.Local
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object MockLocalRepository: LocalRepository {
    override fun getTodasCategorias() = flowOf(mockCategorias)

    override fun getLocaisPorCategoria(categoriaId: String) =
        flowOf(mockLocais.filter { it.categoria.id == categoriaId })

    override fun getLocalDetalhes(localId: String): Flow<Local?> =
        flowOf(mockLocais.find { it.id == localId })
}