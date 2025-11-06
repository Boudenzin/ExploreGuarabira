package com.example.exploreguarabiraapp.data

import androidx.compose.ui.graphics.Color

val mockCategorias = listOf(
    Categoria(id = "cafes", nome = "Cafeterias", iconResId = android.R.drawable.ic_menu_search, cor = Color(0xFF6D4C41)),
    Categoria(id = "restaurantes", nome = "Restaurantes", iconResId = android.R.drawable.ic_menu_agenda, cor = Color(0xFFD32F2F)),
    Categoria(id = "tapiocarias", nome = "Tapiocarias", iconResId = android.R.drawable.ic_menu_edit, cor = Color(0xFFE57373)),
    Categoria(id = "centros-esportivos", nome = "Fitness & Esportes", iconResId = android.R.drawable.ic_menu_today, cor = Color(0xFF1E88E5)),
    Categoria(id = "shopping-centers", nome = "Lojas & Shopping", iconResId = android.R.drawable.ic_menu_recent_history, cor = Color(0xFF43A047)),
)