package com.example.exploreguarabiraapp.data.repository

import com.example.exploreguarabiraapp.data.datasource.LocalDataSource
import com.example.exploreguarabiraapp.data.models.Categoria
import kotlinx.coroutines.flow.flowOf

class LocalRepositoryImpl(
    private val dataSource: LocalDataSource
) : LocalRepository {

    override fun getTodasCategorias() =
        flowOf(dataSource.getCategorias())

    override fun getLocaisPorCategoria(categoriaId: String) =
        flowOf(
            dataSource.getLocais()
                .filter { it.categoria.id == categoriaId }
        )

    override fun getLocalDetalhes(localId: String) =
        flowOf(
            dataSource.getLocais()
                .find { it.id == localId }
        )

    override fun getCategoriaPorId(id: String): Categoria =
        dataSource.getCategorias()
            .firstOrNull { it.id == id }
            ?: error("Categoria não encontrada")
}
