package com.example.exploreguarabiraapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.exploreguarabiraapp.data.models.Categoria
import com.example.exploreguarabiraapp.data.repository.LocalRepository
import com.example.exploreguarabiraapp.ui.components.DashboardTopBar
import com.example.exploreguarabiraapp.ui.theme.DashboardGradientBottomDark
import com.example.exploreguarabiraapp.ui.theme.DashboardGradientBottomLight
import com.example.exploreguarabiraapp.ui.theme.DashboardGradientTopDark
import com.example.exploreguarabiraapp.ui.theme.DashboardGradientTopLight
import com.example.exploreguarabiraapp.utils.LocalAdaptiveLayout
import com.example.exploreguarabiraapp.utils.adaptive.AdaptiveLayout

@Composable
fun DashboardScreen(
    repository: LocalRepository,
    onCategorySelected: (Categoria) -> Unit
) {

    val adaptiveLayout = LocalAdaptiveLayout.current


    val columns = when (adaptiveLayout) {
        AdaptiveLayout.COMPACT -> 2
        AdaptiveLayout.MEDIUM -> 3
        AdaptiveLayout.EXPANDED -> 4
    }

    val categoriaState by repository.getTodasCategorias()
        .collectAsState(initial = emptyList())

    val isDark = isSystemInDarkTheme()

    val gradientColors = if (isDark) {
        listOf(
            DashboardGradientTopDark,
            DashboardGradientBottomDark
        )
    } else {
        listOf(
            DashboardGradientTopLight,
            DashboardGradientBottomLight
        )
    }


    Scaffold (
        topBar = {
            DashboardTopBar()
        }
    ){ paddingValues ->

        Box(
            modifier = Modifier.fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(gradientColors)
                )
                .padding(paddingValues)
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(columns),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(categoriaState) { categoria ->
                    CategoryCard(
                        categoria = categoria,
                        onClick = onCategorySelected,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    )
                }
            }
        }
    }
}