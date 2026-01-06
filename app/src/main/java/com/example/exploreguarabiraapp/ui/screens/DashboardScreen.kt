package com.example.exploreguarabiraapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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

@Composable
fun DashboardScreen(
    repository: LocalRepository,
    onCategorySelected: (Categoria) -> Unit
) {

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


            Column(
                modifier = Modifier.fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                FlowRow(
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    categoriaState.forEach { categoria ->
                        CategoryCard(
                            categoria = categoria,
                            onClick = onCategorySelected,
                            modifier = Modifier
                                .width(160.dp)
                                .height(190.dp)
                        )
                    }
                }
            }
        }
    }
}