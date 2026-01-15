package com.example.exploreguarabiraapp.data.mapper
import com.example.exploreguarabiraapp.data.datasource.local.json.AvaliacaoDto
import com.example.exploreguarabiraapp.data.models.Avaliacao

fun AvaliacaoDto.toModel(): Avaliacao {
    return Avaliacao(
        nomeUsuario = autor,
        nota = nota,
        comentario = comentario
    )
}
