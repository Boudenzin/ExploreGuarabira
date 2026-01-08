package com.example.exploreguarabiraapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.exploreguarabiraapp.data.repository.LocalRepositoryInstance
import com.example.exploreguarabiraapp.ui.theme.LocalSpacing
import com.example.exploreguarabiraapp.ui.viewmodel.LocaisListViewModel
import com.example.exploreguarabiraapp.ui.viewmodel.LocaisListViewModelFactory
import com.example.exploreguarabiraapp.utils.LocalAdaptiveLayout
import com.example.exploreguarabiraapp.utils.adaptive.AdaptiveLayout

@Composable
fun LocaisListScreen(
    categoriaId: String,
    navController: NavController,
    viewModel: LocaisListViewModel = viewModel(
        factory = LocaisListViewModelFactory(
            repository = LocalRepositoryInstance,
            categoriaId = categoriaId
        )
    )

) {

    val adaptiveLayout = LocalAdaptiveLayout.current
    val spacing = LocalSpacing.current

    val contextMaxWidth = when (adaptiveLayout) {
        AdaptiveLayout.COMPACT -> Modifier.fillMaxWidth()
        AdaptiveLayout.MEDIUM -> Modifier.widthIn(max = 720.dp)
        AdaptiveLayout.EXPANDED -> Modifier.widthIn(max = 840.dp)
    }


    //Coleta do estado completo
    val uiState by viewModel.uiState.collectAsState()

    //Configura o uso da snackbar
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.snackbarMessage) {
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
            LocaisTopBar(
                title = uiState.categoria.nome,
                onBack = { navController.popBackStack() }
            )

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .imePadding(),
            verticalArrangement = Arrangement.Top

        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .then(contextMaxWidth)
                ) {
                    OutlinedTextField(
                        value = uiState.searchQuery,
                        onValueChange = { viewModel.onSearchTextChange(it) },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        placeholder = {
                            Text(
                                text = "Buscar estabelecimentos...",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                defaultKeyboardAction(ImeAction.Search)
                            }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = spacing.md,
                                vertical = spacing.sm
                            ),
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

                        uiState.locaisFiltrados.isNotEmpty() -> {
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(spacing.md),
                                verticalArrangement = Arrangement.spacedBy(spacing.sm)
                            ) {
                                items(uiState.locaisFiltrados) { local ->
                                    LocalListItemCard(local = local) {
                                        viewModel.onLocalSelected(local)
                                    }
                                }
                            }
                        }

                        uiState.searchQuery.isNotEmpty() && uiState.locaisFiltrados.isEmpty() -> {
                            Box(
                                modifier = Modifier.fillMaxWidth()
                                    .padding(top = spacing.lg),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = "Nenhum local encontrado para a busca: \"${uiState.searchQuery}\"",
                                    style = MaterialTheme.typography.bodyLarge,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        else -> {
                            Box(
                                modifier = Modifier.fillMaxSize()
                                    .padding(top = spacing.lg),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Não há locais disponíveis nessa categoria",
                                    style = MaterialTheme.typography.bodyLarge,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                    }
                }
            }

            uiState.selectedLocal?.let { local ->
                LocalDetailsSheet(
                    local = local,
                    onDismiss = { viewModel.onLocalSelected(null) }
                )
            }
        }
    }

}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun LocaisListScreenPreview() {
    LocaisListScreen(
        categoriaId = "cafes",
        navController = NavHostController(LocalContext.current)
    )
}
