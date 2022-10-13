package com.fayaz.todo_jc.design_kit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
  primary = DeepPurple600,
  primaryVariant = DeepPurpleLight,
  secondary = Green800
)

private val LightColorPalette = lightColors(
  primary = DeepPurple600,
  primaryVariant = DeepPurpleLight,
  secondary = Green800
)

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
  val colors = if (darkTheme) {
    DarkColorPalette
  } else {
    LightColorPalette
  }

  MaterialTheme(
    colors = colors,
    typography = Typography,
    shapes = Shapes,
    content = content
  )
}
