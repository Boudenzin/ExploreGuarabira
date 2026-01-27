package com.example.exploreguarabiraapp.data.mapper

import com.example.exploreguarabiraapp.data.datasource.local.json.LocalDto
import com.example.exploreguarabiraapp.data.models.Categoria
import com.example.exploreguarabiraapp.data.models.Local

fun LocalDto.toModel(categoria: Categoria): Local {
    return Local(
        id = id,
        nome = nome,
        descricao = descricao,
        categoria = categoria,
        endereco = endereco,
        horario = horario,
        telefone = telefone,
        conhecidoPor = conhecidoPor ?: emptyList(),
        imageUrl = imageUrl,
        avaliacoes = avaliacoes?.map { it.toModel() } ?: emptyList()
    )
}
