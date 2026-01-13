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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import com.example.exploreguarabiraapp.data.models.Local
import kotlinx.coroutines.launch
import com.example.exploreguarabiraapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalDetailsSheet(
    local: Local,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val scope = rememberCoroutineScope()

    val isCollapsed = sheetState.currentValue == SheetValue.PartiallyExpanded

    val buttonText = stringResource(
        if (isCollapsed)
            R.string.sheet_expand
        else
            R.string.sheet_collapse
    )

    val stateDescriptionText = stringResource(
        if (isCollapsed)
            R.string.sheet_state_collapsed
        else
            R.string.sheet_state_expanded
    )



    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        TextButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .semantics {
                    stateDescription = stateDescriptionText
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
                text = buttonText
            )
        }


        LocalDetailsContent(local = local)
    }
}
