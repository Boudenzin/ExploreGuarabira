package com.example.exploreguarabiraapp.data.datasource.local.json

data class LocalDto(
    val id: String,
    val nome: String,
    val descricao: String,
    val categoriaId: String,
    val endereco: String,
    val horario: String,
    val telefone: String?,
    val conhecidoPor: List<String>?,
    val imageUrl: String?,
    val avaliacoes: List<AvaliacaoDto>?
)