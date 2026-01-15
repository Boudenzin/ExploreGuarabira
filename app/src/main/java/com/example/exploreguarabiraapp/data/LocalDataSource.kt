package com.example.exploreguarabiraapp.data

import com.example.exploreguarabiraapp.data.models.Categoria
import com.example.exploreguarabiraapp.data.models.Local

interface LocalDataSource {
    fun getCategorias(): List<Categoria>
    fun getLocais(): List<Local>
}