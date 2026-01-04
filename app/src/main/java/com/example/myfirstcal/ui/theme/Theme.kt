package com.example.myfirstcal.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Purple500,
    onPrimary = Color.White,
    secondary = Purple700,
    background = Color(0xFFF2F2F2),
    surface = Color.White,
    onSurface = Color.Black,
)

private val DarkColors = darkColorScheme(
    primary = Purple200,
    onPrimary = Color.Black,
    secondary = Purple200,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White,
)

/**
 * Application theme wrapper that switches between light/dark palettes.
 */
@Composable
fun MyFirstcalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}