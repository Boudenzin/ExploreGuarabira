package com.example.exploreguarabiraapp.utils

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

enum class DeviceType {
    PHONE,
    TABLET,
    DESKTOP
}

fun deviceTypeFromWidth(widthSizeClass: WindowWidthSizeClass): DeviceType {
    return when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> DeviceType.PHONE
        WindowWidthSizeClass.Medium -> DeviceType.TABLET
        WindowWidthSizeClass.Expanded -> DeviceType.DESKTOP
        else -> DeviceType.PHONE
    }
}