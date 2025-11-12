package com.example.exploreguarabiraapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.exploreguarabiraapp.ui.viewmodel.LocaisListViewModel
import androidx.compose.ui.unit.dp

@Composable
fun LocaisListScreen(
    viewModel: LocaisListViewModel = viewModel(),
    categoriaId: String,
    categoriaNome: String,
    navController: NavController

) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(categoriaId) {
        viewModel.carregarLocaisPorCategoria(categoriaId)
    }

    Scaffold (
        topBar = {
            LocaisTopBar(title = categoriaNome, onBack = {navController.popBackStack()})

        }
    ){
        innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFF7F7F7))
        ) {
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = viewModel::onSearchQueryChanged,
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                placeholder = { Text("Buscar estabelecimentos...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            )
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocaisTopBar(
    title: String,
    onBack: ( () -> Unit)? = null
) {
    TopAppBar(
        title = {Text(title)},
        navigationIcon = {
            if (onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                }
            }
        }
    )
}