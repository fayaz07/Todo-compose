package com.fayaz.todo_jc.design_kit.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fayaz.todo_jc.design_kit.theme.IconTintColor
import com.fayaz.todo_jc.design_kit.theme.Spacing4
import com.fayaz.todo_jc.design_kit.theme.Spacing8

@Composable
fun AddImageComposable(
  modifier: Modifier = Modifier,
  size: Dp = 100.dp,
  onClick: () -> Unit
) {
  IconButton(
    modifier = modifier
      .size(size)
      .border(width = 1.dp, color = IconTintColor, shape = RoundedCornerShape(Spacing4)),
    onClick = onClick
  ) {
    Icon(
      imageVector = Icons.Default.Add,
      contentDescription = "Add image",
      tint = IconTintColor,
      modifier = Modifier
        .size(size)
        .padding(Spacing8)
    )
  }
}

@Preview
@Composable
private fun Preview() {
  AddImageComposable {

  }
}
