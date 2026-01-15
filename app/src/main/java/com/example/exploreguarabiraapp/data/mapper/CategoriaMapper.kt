package com.example.exploreguarabiraapp.data.mapper

import com.example.exploreguarabiraapp.data.datasource.local.json.CategoriaDto
import com.example.exploreguarabiraapp.data.models.Categoria
import com.example.exploreguarabiraapp.R
import com.example.exploreguarabiraapp.ui.theme.CafeColor
import com.example.exploreguarabiraapp.ui.theme.FitnessColor
import com.example.exploreguarabiraapp.ui.theme.RestauranteColor
import com.example.exploreguarabiraapp.ui.theme.ShoppingColor
import com.example.exploreguarabiraapp.ui.theme.TapiocariaColor

fun CategoriaDto.toModel(): Categoria {
    return Categoria(
        id = id,
        nome = nome,
        iconResId = when (icon) {
            "ic_coffee" -> R.drawable.ic_coffee
            "ic_restaurant" -> R.drawable.ic_restaurant
            "ic_tapiocaria" -> R.drawable.ic_tapiocaria
            "ic_fitness_center" -> R.drawable.ic_fitness_center
            else -> R.drawable.ic_shopping_bag
        },
        cor = when (cor) {
            "CAFE" -> CafeColor
            "RESTAURANTE" -> RestauranteColor
            "TAPIOCARIA" -> TapiocariaColor
            "FITNESS" -> FitnessColor
            else -> ShoppingColor
        }
    )
}
