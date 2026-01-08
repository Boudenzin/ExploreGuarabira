package com.example.exploreguarabiraapp.utils.adaptive

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.exploreguarabiraapp.utils.LocalAdaptiveLayout

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun ProvideAdaptiveLayout(
    activity: Activity,
    content: @Composable () -> Unit
) {
    val windowSizeClass = calculateWindowSizeClass(activity)

    val adaptiveLayout = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> AdaptiveLayout.COMPACT
        WindowWidthSizeClass.Medium -> AdaptiveLayout.MEDIUM
        WindowWidthSizeClass.Expanded -> AdaptiveLayout.EXPANDED
        else -> AdaptiveLayout.COMPACT
    }

    CompositionLocalProvider(
        LocalAdaptiveLayout provides adaptiveLayout
    ) {
        content()
    }
}