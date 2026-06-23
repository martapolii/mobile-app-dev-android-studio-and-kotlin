package com.example.midtermcandytemplate.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = CandyPink,
    secondary = CandyMint,
    tertiary = CandyBerry,
    background = CandyCream,
    surface = CandyCream,
    primaryContainer = ColorTokens.primaryContainer,
    secondaryContainer = ColorTokens.secondaryContainer
)

private val DarkColorScheme = darkColorScheme(
    primary = CandyLemon,
    secondary = CandyMint,
    tertiary = CandyPink,
    background = CandyDarkMint,
    surface = CandyDarkMint,
    primaryContainer = CandyBerry,
    secondaryContainer = ColorTokens.darkSecondaryContainer
)

/**
 * Simple candy theme for the study template.
 *
 * Dynamic color is disabled by default so the app keeps the same color identity
 * on every device while you study from screenshots and notes.
 */
@Composable
fun MidtermCandyTemplateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

private object ColorTokens {
    val primaryContainer = CandyLemon
    val secondaryContainer = ColorTokensLight.secondaryContainer
    val darkSecondaryContainer = CandyDarkMint
}

private object ColorTokensLight {
    val secondaryContainer = CandyMint.copy(alpha = 0.25f)
}
