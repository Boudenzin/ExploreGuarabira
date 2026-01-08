package com.example.exploreguarabiraapp.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Spacing(
    val xs: Dp,
    val sm: Dp,
    val md: Dp,
    val lg: Dp,
    val xl: Dp
)

val PhoneSpacing = Spacing(
    xs = 4.dp,
    sm = 8.dp,
    md = 16.dp,
    lg = 24.dp,
    xl = 32.dp
)

val TabletSpacing = Spacing(
    xs = 8.dp,
    sm = 12.dp,
    md = 24.dp,
    lg = 32.dp,
    xl = 48.dp
)

val LocalSpacing = compositionLocalOf { PhoneSpacing }
