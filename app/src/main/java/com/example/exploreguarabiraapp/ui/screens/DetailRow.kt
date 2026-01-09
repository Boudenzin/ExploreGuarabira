package com.example.exploreguarabiraapp.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.exploreguarabiraapp.utils.DetailRowStyle

@Composable
fun DetailRow(
    icon: ImageVector,
    text: String,
    label: String,
    style: DetailRowStyle,
    spokenText: String = "$label: $text"
) {

    val typography = when (style) {
        DetailRowStyle.COMPACT -> MaterialTheme.typography.bodyMedium
        DetailRowStyle.EXPANDED -> MaterialTheme.typography.bodyLarge
    }

    val iconSize = when (style) {
        DetailRowStyle.COMPACT -> 16.dp
        DetailRowStyle.EXPANDED -> 20.dp
    }

    val verticalPadding = when (style) {
        DetailRowStyle.COMPACT -> 2.dp
        DetailRowStyle.EXPANDED -> 6.dp
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = verticalPadding)
            .then(
                if (style == DetailRowStyle.EXPANDED) {
                    Modifier.semantics(mergeDescendants = true) {
                        contentDescription = spokenText
                    }
                } else {
                    Modifier
                }
            )
    )
    {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(iconSize),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = text,
            style = typography
        )
    }
}