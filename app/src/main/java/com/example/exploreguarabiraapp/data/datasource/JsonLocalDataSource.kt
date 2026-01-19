package com.example.exploreguarabiraapp.data.datasource

import android.content.Context
import com.example.exploreguarabiraapp.data.LocalDataSource
import com.example.exploreguarabiraapp.data.datasource.local.json.CategoriaDto
import com.example.exploreguarabiraapp.data.datasource.local.json.LocalDto
import com.example.exploreguarabiraapp.data.mapper.toModel
import com.example.exploreguarabiraapp.data.models.Categoria
import com.example.exploreguarabiraapp.data.models.Local
import com.google.gson.Gson

class JsonLocalDataSource(
    private val context: Context,
    private val gson: Gson = Gson()
): LocalDataSource {


    private val categoriasCache: List<Categoria> by lazy {
        loadCategorias()
    }

    private val locaisCache: List<Local> by lazy {
        loadLocais(categoriasCache)
    }

    override fun getCategorias(): List<Categoria> = categoriasCache

    override fun getLocais(): List<Local> = locaisCache

    private fun loadCategorias(): List<Categoria> {
        val json = context.assets.open("categorias.json")
            .bufferedReader().use { it.readText() }

        val dtos = gson.fromJson(json, Array<CategoriaDto>::class.java)
        return dtos.map { it.toModel() }
    }

    private fun loadLocais(categorias: List<Categoria>): List<Local> {
        val json = context.assets.open("locais.json")
            .bufferedReader().use { it.readText() }

        val dtos = gson.fromJson(json, Array<LocalDto>::class.java)

        return dtos.map { dto ->
            val categoria = categorias.firstOrNull { it.id == dto.categoriaId }
                ?: error("Categoria ${dto.categoriaId} não encontrada para local ${dto.id}")

            dto.toModel(categoria)
        }
    }
}
