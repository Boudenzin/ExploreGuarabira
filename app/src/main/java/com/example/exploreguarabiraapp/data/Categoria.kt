package com.example.exploreguarabiraapp.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

/**
 * Representa a categoria do local.
 *
 * NOTA DE MANUTENIBILIDADE:
 * - iconResId: Usamos @DrawableRes para o ícone do Material Design.
 */
data class Categoria(
    val id: String,
    val nome: String,
    @DrawableRes val iconResId: Int,
    val cor: Color
)
