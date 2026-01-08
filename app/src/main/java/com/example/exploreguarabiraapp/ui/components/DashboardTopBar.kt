package com.example.exploreguarabiraapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.exploreguarabiraapp.R
import com.example.exploreguarabiraapp.ui.theme.LocalSpacing
import com.example.exploreguarabiraapp.utils.LocalAdaptiveLayout
import com.example.exploreguarabiraapp.utils.adaptive.AdaptiveLayout

@Composable
fun DashboardTopBar() {

    val adaptiveLayout = LocalAdaptiveLayout.current
    val spacing = LocalSpacing.current

    val titleStyle = when (adaptiveLayout) {
        AdaptiveLayout.COMPACT -> MaterialTheme.typography.titleLarge
        AdaptiveLayout.MEDIUM -> MaterialTheme.typography.headlineSmall
        AdaptiveLayout.EXPANDED -> MaterialTheme.typography.headlineMedium
    }

    val subtitleStyle = when (adaptiveLayout) {
        AdaptiveLayout.COMPACT -> MaterialTheme.typography.bodySmall
        AdaptiveLayout.MEDIUM -> MaterialTheme.typography.bodyMedium
        AdaptiveLayout.EXPANDED -> MaterialTheme.typography.bodyLarge
    }



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = spacing.xl,
                bottom = spacing.md
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.top_dashboard_name),
            style = titleStyle
        )
        Text(
            text = stringResource(R.string.top_dashboard_subtitle),
            style = subtitleStyle,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}