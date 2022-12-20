package com.fayaz.todo_jc.design_kit.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.fayaz.todo_jc.design_kit.theme.TodoAppTypography

@Composable
fun FieldTitle(modifier: Modifier = Modifier, title: String) {
  Text(
    modifier = modifier,
    text = title,
    style = TodoAppTypography.body1.copy(fontWeight = FontWeight.Medium)
  )
}
