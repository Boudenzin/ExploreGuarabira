package com.example.exploreguarabiraapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.exploreguarabiraapp.ExploreGuarabiraApplication
import com.example.exploreguarabiraapp.R
import com.example.exploreguarabiraapp.ui.theme.LocalSpacing
import com.example.exploreguarabiraapp.ui.viewmodel.LocaisListViewModel
import com.example.exploreguarabiraapp.ui.viewmodel.LocaisListViewModelFactory
import com.example.exploreguarabiraapp.utils.LocalAdaptiveLayout
import com.example.exploreguarabiraapp.utils.MasterDetailWeights
import com.example.exploreguarabiraapp.utils.adaptive.AdaptiveLayout

@Composable
fun LocaisListScreen(
    categoriaId: String,
    navController: NavController,
) {

    val context = LocalContext.current
    val app = context.applicationContext as ExploreGuarabiraApplication

    val viewModel: LocaisListViewModel = viewModel(
        factory = LocaisListViewModelFactory(
            repository = app.container.localRepository,
            categoriaId = categoriaId
        )
    )

    val adaptiveLayout = LocalAdaptiveLayout.current
    val spacing = LocalSpacing.current

    val contextMaxWidth = adaptiveContentWidth(adaptiveLayout)

    val weights = masterDetailWeights(adaptiveLayout)



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
                    LocaisSearchBar(
                        query = uiState.searchQuery,
                        onQueryChange = viewModel::onSearchTextChange,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = spacing.md,
                                vertical = spacing.sm
                            )
                    )

                }
            }

            when {
                uiState.isLoading -> {
                    LoadingState()
                }

                uiState.locaisFiltrados.isNotEmpty() -> {
                    LocaisMasterDetail(
                        adaptiveLayout = adaptiveLayout,
                        locals = uiState.locaisFiltrados,
                        selectedLocal = uiState.selectedLocal,
                        onLocalClick = viewModel::onLocalSelected,
                        onDismissDetail = { viewModel.onLocalSelected(null) },
                        contextMaxWidth = contextMaxWidth,
                        masterWeight = weights.master,
                        detailWeight = weights.detail
                    )
                }

                uiState.searchQuery.isNotEmpty() && uiState.locaisFiltrados.isEmpty() -> {
                    LocaisEmptyState(
                        message = stringResource(
                            R.string.no_results_for_query,
                            uiState.searchQuery
                        )
                    )
                }

                else -> {
                    LocaisEmptyState(
                        message = stringResource(R.string.no_results_category),
                        modifier = Modifier.fillMaxSize()
                    )
                }

            }

            if (adaptiveLayout == AdaptiveLayout.COMPACT) {
                uiState.selectedLocal?.let { local ->
                    LocalDetailsSheet(
                        local = local,
                        onDismiss = { viewModel.onLocalSelected(null) }
                    )
                }
            }
        }
    }
}

fun masterDetailWeights(adaptiveLayout: AdaptiveLayout) =
    when (adaptiveLayout) {
        AdaptiveLayout.MEDIUM -> MasterDetailWeights(0.45f, 0.55f)
        AdaptiveLayout.EXPANDED -> MasterDetailWeights(0.4f, 0.6f)
        else -> MasterDetailWeights(1f, 0f)
    }

@Composable
fun adaptiveContentWidth(adaptiveLayout: AdaptiveLayout): Modifier =
    when (adaptiveLayout) {
        AdaptiveLayout.COMPACT -> Modifier.fillMaxWidth()
        AdaptiveLayout.MEDIUM -> Modifier.widthIn(max = 720.dp)
        AdaptiveLayout.EXPANDED -> Modifier.widthIn(max = 840.dp)
    }

