package com.example.exploreguarabiraapp.data

/**
 * Representa uma avaliação de usuário.
 */
data class Avaliacao(
    val nomeUsuario: String,
    val nota: Int, // 1 a 5
    val comentario: String
)