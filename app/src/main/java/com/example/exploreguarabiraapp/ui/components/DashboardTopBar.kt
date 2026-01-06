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
import androidx.compose.ui.unit.dp
import com.example.exploreguarabiraapp.R
import com.example.exploreguarabiraapp.utils.DeviceType

@Composable
fun DashboardTopBar(
    deviceType: DeviceType
) {

    val titleStyle = when (deviceType) {
        DeviceType.PHONE -> MaterialTheme.typography.titleLarge
        DeviceType.TABLET -> MaterialTheme.typography.headlineSmall
        DeviceType.DESKTOP -> MaterialTheme.typography.headlineMedium
    }

    val subtitleStyle = when (deviceType) {
        DeviceType.PHONE -> MaterialTheme.typography.bodySmall
        DeviceType.TABLET -> MaterialTheme.typography.bodyMedium
        DeviceType.DESKTOP -> MaterialTheme.typography.bodyLarge
    }

    val verticalPadding = when (deviceType) {
        DeviceType.PHONE -> 40.dp
        DeviceType.TABLET -> 32.dp
        DeviceType.DESKTOP -> 40.dp
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = verticalPadding,
                bottom = verticalPadding / 2
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