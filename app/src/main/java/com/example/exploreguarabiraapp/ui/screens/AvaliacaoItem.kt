package com.example.exploreguarabiraapp.ui.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.unit.dp
import com.example.exploreguarabiraapp.data.models.Avaliacao
import com.example.exploreguarabiraapp.ui.theme.RatingStarActiveDark
import com.example.exploreguarabiraapp.ui.theme.RatingStarActiveLight
import com.example.exploreguarabiraapp.ui.theme.RatingStarInactiveDark
import com.example.exploreguarabiraapp.ui.theme.RatingStarInactiveLight

@Composable
fun AvaliacaoItem(
    avaliacao: Avaliacao
) {
    val isDark = isSystemInDarkTheme()

    val activeStar = if (isDark) RatingStarActiveDark else RatingStarActiveLight
    val inactiveStar = if (isDark) RatingStarInactiveDark else RatingStarInactiveLight

    Row(
        verticalAlignment = Alignment.Top
    ) {

        // Avatar
        Surface(
            modifier = Modifier.size(40.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Text(
                text = avaliacao.nomeUsuario.first().toString(),
                modifier = Modifier.wrapContentSize(Alignment.Center),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {

            Text(
                text = avaliacao.nomeUsuario,
                style = MaterialTheme.typography.titleSmall
            )

            Row {
                repeat(5) { index ->
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = null,
                        tint = if (index < avaliacao.nota)
                            activeStar
                        else
                            inactiveStar,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Text(
                text = avaliacao.comentario,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

