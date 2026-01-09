package com.example.exploreguarabiraapp.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import com.example.exploreguarabiraapp.data.models.Local
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalDetailsSheet(
    local: Local,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        TextButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .semantics {
                    stateDescription =
                        if (sheetState.currentValue == SheetValue.PartiallyExpanded)
                            "Detalhes recolhidos"
                        else
                            "Detalhes expandidos"
                },
            onClick = {
                scope.launch {
                    if (sheetState.currentValue == SheetValue.PartiallyExpanded) {
                        sheetState.expand()
                    } else {
                        sheetState.partialExpand()
                    }
                }
            }
        ) {
            Text(
                text =
                    if (sheetState.currentValue == SheetValue.PartiallyExpanded)
                        "Expandir detalhes"
                    else
                        "Recolher detalhes"
            )
        }


        LocalDetailsContent(local = local)
    }
}
