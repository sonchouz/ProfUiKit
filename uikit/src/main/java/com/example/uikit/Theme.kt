package com.example.uikit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalUiKitColors = staticCompositionLocalOf<UiKitColors> {
    error("No UiKitColors provided")
}

object UiKitTheme {
    val colors: UiKitColors
        @Composable
        @ReadOnlyComposable
        get() = LocalUiKitColors.current
}

@Composable
fun MyUiKitTheme(
    colors: UiKitColors = com.example.uikit.colors,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalUiKitColors provides colors, content= content)
}