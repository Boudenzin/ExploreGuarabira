package com.example.exploreguarabiraapp.data.models


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
    val endereco: String,
    val horario: String,
    val telefone: String?,
    val conhecidoPor: List<String> = emptyList(),
    val imageUrl: String?,
    val avaliacoes: List<Avaliacao> = emptyList()
) {
    val totalAvaliacoes: Int
        get() = avaliacoes.size

    val avaliacaoMedia: Double?
        get() = if (avaliacoes.isNotEmpty()) {
            avaliacoes.map { it.nota }.average()
        } else null
}