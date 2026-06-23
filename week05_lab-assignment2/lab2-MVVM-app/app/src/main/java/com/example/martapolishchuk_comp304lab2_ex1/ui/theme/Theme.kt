package com.example.martapolishchuk_comp304lab2_ex1.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Marta Polishchuk - 301432299

private val DarkColorScheme = darkColorScheme(
    primary = ElectricTeal,
    onPrimary = NightNavy,
    secondary = SunsetPeach,
    onSecondary = NightNavy,
    tertiary = GoldenAccent,
    onTertiary = NightNavy,
    background = NightNavy,
    onBackground = MoonText,
    surface = DarkSurface,
    onSurface = MoonText,
    surfaceVariant = DarkCard,
    onSurfaceVariant = MoonText,
    primaryContainer = Color(0xFF113F48),
    onPrimaryContainer = MoonText,
    tertiaryContainer = Color(0xFF573222),
    onTertiaryContainer = Color(0xFFFFE7C2),
    outline = DarkOutline,
    error = WarningRose
)

private val LightColorScheme = lightColorScheme(
    primary = MidnightBlue,
    onPrimary = CloudWhite,
    secondary = LagoonTeal,
    onSecondary = CloudWhite,
    tertiary = CoralPop,
    onTertiary = CloudWhite,
    background = CloudWhite,
    onBackground = SlateText,
    surface = SoftCard,
    onSurface = SlateText,
    surfaceVariant = MistBlue,
    onSurfaceVariant = SlateText,
    primaryContainer = Color(0xFFD9E7FF),
    onPrimaryContainer = MidnightBlue,
    tertiaryContainer = ApricotGlow,
    onTertiaryContainer = MidnightBlue,
    outline = Color(0xFF8DA1BB),
    error = WarningRose
)

@Composable
fun MartaPolishchuk_COMP304Lab2_Ex1Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
