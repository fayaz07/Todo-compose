package com.fayaz.todo_jc.design_kit.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fayaz.todo_jc.design_kit.theme.Spacing16

@Composable
fun <T> AppDropDownList(
  label: String,
  value: String,
  items: List<T>,
  width: Dp = 0.dp,
  height: Dp = 200.dp,
  display: (T) -> String,
  onSelected: (T) -> Unit,
  onExpanded: () -> Unit,
  onDismissed: () -> Unit
) {
  var expanded by remember { mutableStateOf(false) }
  val dropDownMenuWidth = if (width == 0.dp) {
    (LocalConfiguration.current.screenWidthDp - 32).dp
  } else {
    width
  }

  Column {
    AppOutlinedTextField(
      modifier = Modifier
        .onFocusChanged {
          if (it.hasFocus || it.isFocused) {
            onExpanded()
            expanded = true
          }
        },
      title = label,
      hint = "",
      value = value,
      onValueChange = {},
      readOnly = true,
    )
    Box(
      modifier = Modifier.padding(horizontal = Spacing16)
    ) {
      DropdownMenu(
        modifier = Modifier
          .width(dropDownMenuWidth)
          .height(height),
        expanded = expanded,
        onDismissRequest = {
          onDismissed()
          expanded = false
        },
      ) {
        items.forEachIndexed { _, s ->
          DropdownMenuItem(
            onClick = {
              onSelected(s)
              expanded = false
            }
          ) {
            Text(text = display(s))
          }
        }
      }
    }
  }
}
