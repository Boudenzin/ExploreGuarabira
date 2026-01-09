package com.example.exploreguarabiraapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.exploreguarabiraapp.data.models.Local
import com.example.exploreguarabiraapp.ui.theme.LocalSpacing

@Composable
fun LocalList(
    locals: List<Local>,
    onItemClick: (Local) -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(spacing.md),
        verticalArrangement = Arrangement.spacedBy(spacing.sm)
    ) {
        items(locals) { local ->
            LocalListItemCard(
                local = local,
                onClick = onItemClick
            )
        }
    }
}
