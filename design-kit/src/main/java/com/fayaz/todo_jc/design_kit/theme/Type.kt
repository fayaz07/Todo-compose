package com.fayaz.todo_jc.design_kit.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val TodoAppTypography = Typography(
  defaultFontFamily = nunitoSansFont,
  h1 = TextStyle(
    fontSize = 96.sp,
    fontWeight = FontWeight.Light
  ),
  h2 = TextStyle(
    fontSize = 60.sp,
    fontWeight = FontWeight.Light
  ),
  h3 = TextStyle(
    fontSize = 48.sp,
    fontWeight = FontWeight.Normal
  ),
  h4 = TextStyle(
    fontSize = 34.sp,
    fontWeight = FontWeight.Normal
  ),
  h5 = TextStyle(
    fontSize = 24.sp,
    fontWeight = FontWeight.Normal
  ),
  h6 = TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight.Medium
  ),
  subtitle1 = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal
  ),
  subtitle2 = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.Medium
  ),
  body1 = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal
  ),
  body2 = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.Normal
  ),
  button = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.Bold
  ),
  caption = TextStyle(
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal
  ),
  overline = TextStyle(
    fontSize = 10.sp,
    fontWeight = FontWeight.Normal
  ),
)

fun TextStyle.color(color: Color): TextStyle {
  return copy(color = color)
}
