package com.example.exploreguarabiraapp.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.example.exploreguarabiraapp.utils.LocalAdaptiveLayout
import com.example.exploreguarabiraapp.utils.adaptive.AdaptiveLayout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocaisTopBar(
    title: String,
    onBack: (() -> Unit)? = null
) {
    val adaptiveLayout = LocalAdaptiveLayout.current

    val titleStyle = when (adaptiveLayout) {
        AdaptiveLayout.COMPACT -> MaterialTheme.typography.titleLarge
        AdaptiveLayout.MEDIUM -> MaterialTheme.typography.headlineSmall
        AdaptiveLayout.EXPANDED -> MaterialTheme.typography.headlineMedium
    }

    TopAppBar(
        title = {
            Text(
                text = title,
                style = titleStyle,
                maxLines = 1
            )
        },
        navigationIcon = {
            if (onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar"
                    )
                }
            }
        }
    )
}
