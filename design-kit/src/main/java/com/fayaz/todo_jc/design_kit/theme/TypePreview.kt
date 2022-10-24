package com.fayaz.todo_jc.design_kit.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun PreviewTodoAppTypography() {
  TodoAppTypographyComposables()
}

@Composable
fun TodoAppTypographyComposables() {
  Surface(
    modifier = Modifier.fillMaxSize()
  ) {
    Column(
      modifier = Modifier.padding(32.dp)
    ) {
      Text(text = "H1", style = TodoAppTypography.h1)
      Text(text = "H2", style = TodoAppTypography.h2)
      Text(text = "H3", style = TodoAppTypography.h3)
      Text(text = "H4", style = TodoAppTypography.h4)
      Text(text = "H5", style = TodoAppTypography.h5)
      Text(text = "H6", style = TodoAppTypography.h6)
      Text(text = "Subtitle 1", style = TodoAppTypography.subtitle1)
      Text(text = "Subtitle 2", style = TodoAppTypography.subtitle2)
      Text(text = "Body 1", style = TodoAppTypography.body1)
      Text(text = "Body 2", style = TodoAppTypography.body2)
      Button(onClick = {}) {
        Text(text = "BUTTON", style = TodoAppTypography.button)
      }
      Text(text = "Caption", style = TodoAppTypography.caption)
      Text(text = "OVERLINE", style = TodoAppTypography.overline)
    }
  }
}
