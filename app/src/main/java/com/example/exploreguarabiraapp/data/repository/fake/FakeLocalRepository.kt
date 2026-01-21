package com.example.exploreguarabiraapp.data.repository.fake

import androidx.compose.ui.graphics.Color
import com.example.exploreguarabiraapp.data.models.Categoria
import com.example.exploreguarabiraapp.data.models.Local
import com.example.exploreguarabiraapp.data.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import com.example.exploreguarabiraapp.R

class FakeLocalRepository : LocalRepository {

    private val categorias = listOf(
        Categoria(
            id = "cafes",
            nome = "Cafés",
            iconResId = R.drawable.ic_coffee,
            cor = Color(0xFF795548)
        ),
        Categoria(
            id = "academias",
            nome = "Academias",
            iconResId = R.drawable.ic_fitness_center,
            cor = Color(0xFF4CAF50)
        )
    )

    private val locais = listOf(
        Local(
            id = "cafe-1",
            nome = "Café Teste",
            descricao = "Café fictício para testes de UI",
            categoria = categorias[0],
            avaliacaoMedia = 4.8,
            totalAvaliacoes = 120,
            endereco = "Rua Teste, 123",
            horario = "Seg-Sex 8h-18h",
            telefone = "0000-0000",
            conhecidoPor = listOf("Café forte", "Ambiente calmo"),
            imageUrl = "",
            avaliacoes = emptyList()
        )
    )

    override fun getTodasCategorias(): Flow<List<Categoria>> =
        flowOf(categorias)

    override fun getLocaisPorCategoria(categoriaId: String): Flow<List<Local>> =
        flowOf(locais.filter { it.categoria.id == categoriaId })

    override fun getLocalDetalhes(localId: String): Flow<Local?> =
        flowOf(locais.find { it.id == localId })

    override fun getCategoriaPorId(id: String): Categoria =
        categorias.first { it.id == id }
}