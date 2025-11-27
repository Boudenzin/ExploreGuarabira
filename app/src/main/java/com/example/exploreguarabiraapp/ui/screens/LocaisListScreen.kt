package com.example.exploreguarabiraapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.exploreguarabiraapp.ui.viewmodel.LocaisListViewModel
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.exploreguarabiraapp.R
import com.example.exploreguarabiraapp.data.models.Categoria
import com.example.exploreguarabiraapp.data.repository.LocalRepositoryInstance
import com.example.exploreguarabiraapp.ui.viewmodel.LocaisListViewModelFactory

@Composable
fun LocaisListScreen(
    categoria: Categoria,
    navController: NavController,
    viewModel: LocaisListViewModel = viewModel(
        factory = LocaisListViewModelFactory(LocalRepositoryInstance, categoria)
    )

) {
    //Coleta do estado completo
    val uiState by viewModel.uiState.collectAsState()

    //Configura o uso da snackbar
    val snackbarHostState = remember { SnackbarHostState() }

    val snackbarMesasge = rememberUpdatedState(uiState.snackbarMessage)


    LaunchedEffect(snackbarMesasge) {
        uiState.snackbarMessage?.let { message ->
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = "OK",
                duration = SnackbarDuration.Short
            )
            viewModel.onSnackbarShown()
        }
    }

    Scaffold (
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        topBar = {
            LocaisTopBar(title = categoria.nome, onBack = {navController.popBackStack()})

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
                onValueChange = { viewModel.onSearchTextChange(it) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                placeholder = { Text("Buscar estabelecimentos...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(24.dp)

            )
            when {
                uiState.isLoading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }
                }
            }
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

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun LocaisListScreenPreview() {
    LocaisListScreen(
        categoria = Categoria(
            id = "cafes",
            nome = "Cafeterias",
            iconResId = R.drawable.ic_search,
            cor = Color(0xFF6D4C41)
        ),
        navController = NavHostController(LocalContext.current),
        viewModel = LocaisListViewModel(
            repository = LocalRepositoryInstance,
            categoriaInicial = Categoria(
                id = "cafes",
                nome = "Cafeterias",
                iconResId = R.drawable.ic_search,
                cor = Color(0xFF6D4C41)
            )
        )
    )
}
