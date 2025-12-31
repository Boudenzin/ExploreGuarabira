package com.example.exploreguarabiraapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.exploreguarabiraapp.data.models.Categoria
import com.example.exploreguarabiraapp.data.repository.LocalRepository

@Composable
fun DashboardScreen(
    repository: LocalRepository,
    onCategorySelected: (Categoria) -> Unit
) {

    val categoriaState by repository.getTodasCategorias()
        .collectAsState(initial = emptyList());

    Scaffold (
        topbar = {
            Column(
                modifier =  Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "ExploraGuarabira",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Descubra os melhores lugares da sua cidade",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    ){ paddingValues ->
        Column (
            modifier = Modifier.padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            FlowRow (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                categoriaState.forEach { categoria ->
                    CategoryCard(
                        categoria,
                        onCategorySelected,
                        Modifier
                            .width(150.dp)
                            .height(150.dp)
                    )
                }
            }
        }

    }
}