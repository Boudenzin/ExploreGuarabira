package com.example.exploreguarabiraapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.exploreguarabiraapp.data.models.Categoria
import com.example.exploreguarabiraapp.utils.DeviceType

@Composable
fun CategoryCard(
    deviceType: DeviceType,
    categoria: Categoria,
    onClick: (Categoria) -> Unit,
    modifier: Modifier = Modifier
) {

    val iconBoxSize = when (deviceType) {
        DeviceType.PHONE -> 64.dp
        DeviceType.TABLET -> 72.dp
        DeviceType.DESKTOP -> 80.dp
    }

    val iconSize = when (deviceType) {
        DeviceType.PHONE -> 32.dp
        DeviceType.TABLET -> 40.dp
        DeviceType.DESKTOP -> 48.dp
    }

    val textStyle = when (deviceType) {
        DeviceType.PHONE -> MaterialTheme.typography.titleMedium
        DeviceType.TABLET -> MaterialTheme.typography.titleLarge
        DeviceType.DESKTOP -> MaterialTheme.typography.headlineSmall
    }

    val padding = when (deviceType) {
        DeviceType.PHONE -> 16.dp
        DeviceType.TABLET -> 24.dp
        DeviceType.DESKTOP -> 32.dp
    }


    Card(
        modifier = modifier
            .clickable { onClick(categoria)},
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(iconBoxSize)
                    .background(
                        color = categoria.cor,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = categoria.iconResId),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(iconSize)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = categoria.nome,
                style = textStyle,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis

            )

        }
    }
}