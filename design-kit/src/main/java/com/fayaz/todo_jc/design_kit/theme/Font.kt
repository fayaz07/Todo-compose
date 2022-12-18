package com.fayaz.todo_jc.design_kit.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontLoadingStrategy
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.fayaz.todo_jc.design_kit.R

val nunitoSansFont = FontFamily(
  Font(
    resId = R.font.nunito_sans_extra_light_italic,
    weight = FontWeight.ExtraLight,
    style = FontStyle.Italic,
    loadingStrategy = FontLoadingStrategy.OptionalLocal
  ),
  Font(
    resId = R.font.nunito_sans_extra_light,
    weight = FontWeight.ExtraLight,
    loadingStrategy = FontLoadingStrategy.OptionalLocal
  ),
  Font(
    resId = R.font.nunito_sans_light,
    weight = FontWeight.Light,
    loadingStrategy = FontLoadingStrategy.OptionalLocal
  ),
  Font(
    resId = R.font.nunito_sans_light_italic,
    weight = FontWeight.Light,
    style = FontStyle.Italic,
    loadingStrategy = FontLoadingStrategy.OptionalLocal
  ),
  Font(
    resId = R.font.nunito_sans_regular,
    weight = FontWeight.Normal,
  ),
  Font(
    resId = R.font.nunito_sans_semi_bold,
    weight = FontWeight.SemiBold,
  ),
  Font(
    resId = R.font.nunito_sans_semi_bold,
    weight = FontWeight.Medium,
  ),
  Font(
    resId = R.font.nunito_sans_semi_bold_italic,
    weight = FontWeight.SemiBold,
    style = FontStyle.Italic,
    loadingStrategy = FontLoadingStrategy.OptionalLocal
  ),
  Font(
    resId = R.font.nunito_sans_bold,
    weight = FontWeight.Bold,
  ),
  Font(
    resId = R.font.nunito_sans_bold_italic,
    weight = FontWeight.Bold,
    style = FontStyle.Italic,
    loadingStrategy = FontLoadingStrategy.OptionalLocal
  ),
  Font(
    resId = R.font.nunito_sans_extra_bold,
    weight = FontWeight.ExtraBold
  ),
  Font(
    resId = R.font.nunito_sans_extra_bold_italic,
    weight = FontWeight.ExtraBold,
    style = FontStyle.Italic,
    loadingStrategy = FontLoadingStrategy.OptionalLocal
  ),
  Font(
    resId = R.font.nunito_sans_black,
    weight = FontWeight.Black,
    loadingStrategy = FontLoadingStrategy.OptionalLocal
  ),
  Font(
    resId = R.font.nunito_sans_black_italic,
    weight = FontWeight.Black,
    style = FontStyle.Italic,
    loadingStrategy = FontLoadingStrategy.OptionalLocal
  ),
)
