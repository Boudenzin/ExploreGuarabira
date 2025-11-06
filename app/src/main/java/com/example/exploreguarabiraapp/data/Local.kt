package com.example.exploreguarabiraapp.data

import androidx.annotation.DrawableRes


/**
 * Representa um local na cidade (Café, Restaurante, etc.).
 *
 * NOTA DE MANUTENIBILIDADE:
 * - imageResId: Usamos @DrawableRes para a fase mock. Na fase API, este campo seria trocado
 * por val imageUrl: String, e o LocalScreen usaria a biblioteca Coil/Glide.
 * - String: Manter como String, pois os dados virão assim da API.
 */
data class Local(
    val id: String,
    val nome: String,
    val descricao: String,
    val categoria: Categoria,
    val avaliacaoMedia: Double,
    val totalAvaliacoes: Int,
    val preco: String, // Ex: "$", "$$", "$$$"
    val endereco: String,
    val horario: String,
    val telefone: String,
    val conhecidoPor: List<String>,
    val imageUrl: String,
    val avaliacoes: List<Avaliacao>
)