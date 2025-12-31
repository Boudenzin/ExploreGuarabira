package com.example.exploreguarabiraapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.exploreguarabiraapp.data.models.Avaliacao

@Composable
fun AvaliacaoItem(
    avaliacao: Avaliacao
) {
    Surface(
        modifier = Modifier.size(40.dp),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Text (
            text = avaliacao.nomeUsuario.first().toString(),
            modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleMedium
        )
    }

    Spacer(modifier = Modifier.width(8.dp))

    Column {
        Text(
            text = avaliacao.nomeUsuario.first().toString(),
            style = MaterialTheme.typography.titleMedium
        )
    }

    Spacer(modifier = Modifier.width(8.dp))

    Column {
        Text(
            text = avaliacao.nomeUsuario,
            style = MaterialTheme.typography.titleSmall
        )

        Row {
            repeat(5) { index ->
                Icon (
                    Icons.Filled.Star,
                    contentDescription = null,
                    tint = if (index < avaliacao.nota) Color(0xFFFFC107) else Color.LightGray,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Text(avaliacao.comentario,
            style = MaterialTheme.typography.bodyMedium)
    }
}
