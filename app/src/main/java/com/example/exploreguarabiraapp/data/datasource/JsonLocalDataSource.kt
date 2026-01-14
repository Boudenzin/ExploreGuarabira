package com.example.exploreguarabiraapp.data.datasource

import android.content.Context
import com.example.exploreguarabiraapp.data.models.Categoria
import com.example.exploreguarabiraapp.data.models.Local

class JsonLocalDataSource(
    private val context: Context
) : LocalDataSource {

    override fun getCategorias(): List<Categoria> {
        // lê categorias.json
    }

    override fun getLocais(): List<Local> {
        // lê locais.json
    }
}