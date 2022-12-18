package com.fayaz.todo_jc.design_kit.composables

import android.app.TimePickerDialog
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun TimePicker(
  label: String,
  value: String,
  keyboardActions: KeyboardActions = KeyboardActions.Default,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  is24HourView: Boolean = true,
  onValueChange: (Int, Int) -> Unit,
) {
  val focusManager = LocalFocusManager.current
  val context = LocalContext.current
  val dialog = remember {
    TimePickerDialog(
      context,
      { _, hour, minute ->
        onValueChange(hour, minute)
        focusManager.clearFocus()
      },
      5,
      0,
      is24HourView,
    )
  }

  LaunchedEffect(Unit) {
    dialog.setOnDismissListener {
      focusManager.clearFocus()
    }
  }

  AppOutlinedTextField(
    title = label,
    value = value,
    readOnly = true,
    modifier = Modifier.onFocusChanged {
      if (it.hasFocus || it.isFocused) {
        dialog.show()
      }
    },
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    onValueChange = {}
  )
}
