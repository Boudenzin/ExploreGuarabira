package com.example.exploreguarabiraapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.exploreguarabiraapp.R
import com.example.exploreguarabiraapp.data.models.Local
import com.example.exploreguarabiraapp.ui.theme.LocalSpacing
import com.example.exploreguarabiraapp.ui.theme.RatingStarActiveDark
import com.example.exploreguarabiraapp.ui.theme.RatingStarActiveLight
import com.example.exploreguarabiraapp.utils.DetailRowStyle
import com.example.exploreguarabiraapp.utils.LocalAdaptiveLayout
import com.example.exploreguarabiraapp.utils.adaptive.AdaptiveLayout

@Composable
fun LocalListItemCard(
    local: Local,
    onClick: (Local) -> Unit)
{

    val adaptiveLayout = LocalAdaptiveLayout.current
    val spacing = LocalSpacing.current
    val isDark = isSystemInDarkTheme()

    val activeStar = if (isDark) RatingStarActiveDark else RatingStarActiveLight

    val imageSize = when (adaptiveLayout) {
        AdaptiveLayout.COMPACT -> 96.dp
        AdaptiveLayout.MEDIUM -> 104.dp
        AdaptiveLayout.EXPANDED -> 140.dp
    }

    val contentPadding = when (adaptiveLayout) {
        AdaptiveLayout.COMPACT -> spacing.sm
        AdaptiveLayout.MEDIUM -> spacing.md
        AdaptiveLayout.EXPANDED -> spacing.lg
    }

    val titleStyle = when (adaptiveLayout) {
        AdaptiveLayout.COMPACT -> MaterialTheme.typography.titleMedium
        AdaptiveLayout.MEDIUM -> MaterialTheme.typography.titleLarge
        AdaptiveLayout.EXPANDED -> MaterialTheme.typography.headlineSmall
    }

    val bodyStyle = when (adaptiveLayout) {
        AdaptiveLayout.COMPACT -> MaterialTheme.typography.bodySmall
        else -> MaterialTheme.typography.bodyMedium
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 520.dp)
            .clickable {onClick(local)},
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(contentPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(imageSize)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = local.imageUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.placeholder),
                    error = painterResource(R.drawable.placeholder)
                )

            }

            Spacer(modifier = Modifier.width(spacing.md))

            Column (
                modifier = Modifier.weight(1f)
            ){
                Text (
                    text = local.nome,
                    style = titleStyle
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = stringResource(R.string.rating_content_description),
                        tint = activeStar,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(
                            R.string.local_rating_short,
                            local.avaliacaoMedia,
                            local.totalAvaliacoes,
                        ),
                        style = bodyStyle
                    )
                }

                Spacer(modifier = Modifier.height(spacing.xs))

                DetailRow(
                    icon = Icons.Default.LocationOn,
                    text = local.endereco,
                    label = stringResource(R.string.label_endereco),
                    style = DetailRowStyle.COMPACT

                )

                DetailRow(
                    icon = Icons.Default.Schedule,
                    text = local.horario,
                    label = stringResource(R.string.label_atendimento),
                    style = DetailRowStyle.COMPACT
                )
            }
        }

    }
}