package com.example.exploreguarabiraapp.utils

import androidx.compose.runtime.compositionLocalOf
import com.example.exploreguarabiraapp.utils.adaptive.AdaptiveLayout

val LocalAdaptiveLayout = compositionLocalOf {
    AdaptiveLayout.COMPACT
}