package com.example.exploreguarabiraapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.exploreguarabiraapp.R
import com.example.exploreguarabiraapp.data.models.Local
import com.example.exploreguarabiraapp.ui.theme.RatingStarActiveDark
import com.example.exploreguarabiraapp.ui.theme.RatingStarActiveLight
import com.example.exploreguarabiraapp.utils.DetailRowStyle

@Composable
fun LocalDetailsContent(
    local: Local,
    modifier: Modifier = Modifier
) {
    val isDark = isSystemInDarkTheme()
    val activeStar = if (isDark) RatingStarActiveDark else RatingStarActiveLight

    val imageDescription = stringResource(
        R.string.local_image_description,
        local.nome
    )

    val temAvaliacao = (local.avaliacaoMedia ?: 0.0) > 0.0 && local.totalAvaliacoes > 0

    val ratingText = if (temAvaliacao) {
        stringResource(
            R.string.local_rating_text,
            local.avaliacaoMedia!!,
            local.totalAvaliacoes
        )
    } else {
        stringResource(R.string.local_rating_empty)
    }


    val ratingA11yText = if (temAvaliacao) {
        stringResource(
            R.string.local_rating_accessibility,
            local.avaliacaoMedia!!,
            local.totalAvaliacoes
        )
    } else ratingText


    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .semantics {
                    contentDescription = imageDescription
                },
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = local.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.placeholder),
                fallback = painterResource(R.drawable.placeholder)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = local.nome,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.semantics { heading() }
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = activeStar
            )
            Text(
                text = ratingText,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.semantics {
                    contentDescription = ratingA11yText
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.details_sheet_about),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.semantics { heading() }
        )

        Text(
            text = local.descricao,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (local.conhecidoPor.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.details_sheet_known),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.semantics { heading() }
            )
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                local.conhecidoPor.forEach { tag ->
                    AssistChip(
                        onClick = {},
                        label = { Text(tag) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.details_sheet_details),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.semantics { heading() }
        )

        DetailRow(
            icon = Icons.Default.LocationOn,
            text = local.endereco,
            label = stringResource(R.string.label_endereco),
            style = DetailRowStyle.EXPANDED
        )

        DetailRow(
            icon = Icons.Default.Schedule,
            text = local.horario,
            label = stringResource(R.string.label_atendimento),
            style = DetailRowStyle.EXPANDED
        )

        if (!local.telefone.isNullOrBlank()) {
            DetailRow(
                icon = Icons.Default.Phone,
                text = local.telefone,
                label = stringResource(R.string.label_telefone),
                style = DetailRowStyle.EXPANDED
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (local.avaliacoes.isNotEmpty()) {
            Text(
                text = stringResource(R.string.details_sheet_recent_ratings),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.semantics { heading() }
            )

            Spacer(modifier = Modifier.height(8.dp))

            local.avaliacoes.forEach { avaliacao ->
                AvaliacaoItem(avaliacao)
                Spacer(modifier = Modifier.height(12.dp))
            }
        } else {
            Text(
                text = stringResource(R.string.details_sheet_no_recent_ratings),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.height(60.dp))
    }
}

