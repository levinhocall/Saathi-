package com.saathi.aiassistant.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object SaathiPalette {
    val Background = Color(0xFF090A0E)
    val Surface = Color(0xFF14151B)
    val SurfaceRaised = Color(0xFF1D1F26)
    val Border = Color(0xFF30323A)
    val Red = Color(0xFFE53945)
    val RedDeep = Color(0xFFB92331)
    val Purple = Color(0xFFB67CFF)
    val Green = Color(0xFF65D39A)
    val Amber = Color(0xFFFFC66D)
    val Text = Color(0xFFF5F4F6)
    val Muted = Color(0xFFB9B7C0)
    val Faint = Color(0xFF85838D)
}

private val SaathiDarkColors = darkColorScheme(
    primary = SaathiPalette.Red,
    onPrimary = SaathiPalette.Text,
    secondary = SaathiPalette.Purple,
    onSecondary = SaathiPalette.Background,
    tertiary = SaathiPalette.Green,
    onTertiary = SaathiPalette.Background,
    background = SaathiPalette.Background,
    onBackground = SaathiPalette.Text,
    surface = SaathiPalette.Surface,
    onSurface = SaathiPalette.Text,
    surfaceVariant = SaathiPalette.SurfaceRaised,
    onSurfaceVariant = SaathiPalette.Muted,
    outline = SaathiPalette.Border,
)

@Composable
fun SaathiTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = SaathiDarkColors,
        content = content,
    )
}
