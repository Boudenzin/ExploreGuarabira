package com.example.exploreguarabiraapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.exploreguarabiraapp.data.models.Local
import com.example.exploreguarabiraapp.utils.adaptive.AdaptiveLayout

@Composable
fun LocaisMasterDetail(
    adaptiveLayout: AdaptiveLayout,
    locals: List<Local>,
    selectedLocal: Local?,
    onLocalClick: (Local) -> Unit,
    onDismissDetail: () -> Unit,
    contextMaxWidth: Modifier,
    masterWeight: Float,
    detailWeight: Float
) {
    when (adaptiveLayout) {

        AdaptiveLayout.COMPACT -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(contextMaxWidth)
            ) {
                LocalList(
                    locals = locals,
                    onItemClick = onLocalClick
                )
            }

            selectedLocal?.let {
                LocalDetailsSheet(
                    local = it,
                    onDismiss = onDismissDetail
                )
            }
        }

        else -> {
            Row(modifier = Modifier.fillMaxSize()) {

                // MASTER
                Box(
                    modifier = Modifier
                        .weight(masterWeight)
                        .widthIn(min = 360.dp)
                ) {
                    LocalList(
                        locals = locals,
                        onItemClick = onLocalClick
                    )
                }

                // DETAIL
                Box(
                    modifier = Modifier.weight(detailWeight)
                ) {
                    selectedLocal?.let {
                        LocalDetailsContent(
                            local = it,
                            modifier = Modifier.fillMaxSize()
                        )
                    } ?: EmptyDetailState()
                }
            }
        }
    }
}
