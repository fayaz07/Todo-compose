package com.fayaz.todo_jc.design_kit.composables

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import com.fayaz.todo_jc.design_kit.theme.ErrorColor
import com.fayaz.todo_jc.design_kit.theme.HintColor
import com.fayaz.todo_jc.design_kit.theme.Spacing16
import com.fayaz.todo_jc.design_kit.theme.Spacing24
import com.fayaz.todo_jc.design_kit.theme.Spacing4
import com.fayaz.todo_jc.design_kit.theme.TodoAppTypography
import com.fayaz.todo_jc.design_kit.theme.color

@Composable
fun AppOutlinedTextField(
  modifier: Modifier = Modifier,
  title: String,
  hint: String = "",
  value: String,
  onValueChange: (String) -> Unit,
  enabled: Boolean = true,
  readOnly: Boolean = false,
  textStyle: TextStyle = TodoAppTypography.subtitle1,
  placeholder: @Composable (() -> Unit)? = null,
  leadingIcon: @Composable (() -> Unit)? = null,
  trailingIcon: @Composable (() -> Unit)? = null,
  error: String = "",
  isError: Boolean = false,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  keyboardActions: KeyboardActions = KeyboardActions.Default,
  singleLine: Boolean = false,
  maxLines: Int = Int.MAX_VALUE,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  shape: Shape = MaterialTheme.shapes.small,
  colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors()
) {
  Column {
    OutlinedTextField(
      modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = Spacing16),
      label = {
        Text(text = title)
      },
      value = value,
      onValueChange = onValueChange,
      enabled = enabled,
      readOnly = readOnly,
      textStyle = textStyle,
      placeholder = placeholder,
      leadingIcon = leadingIcon,
      trailingIcon = trailingIcon,
      isError = isError,
      visualTransformation = visualTransformation,
      keyboardOptions = keyboardOptions,
      keyboardActions = keyboardActions,
      maxLines = maxLines,
      singleLine = singleLine,
      interactionSource = interactionSource,
      shape = shape,
      colors = colors,
    )
    Space(space = Spacing4)
    if (isError && error.isNotEmpty()) {
      HintText(text = error, error = true)
    } else if (hint.isNotEmpty()) {
      HintText(text = hint)
    }
  }
}

@Composable
private fun HintText(text: String, error: Boolean = false) {
  Text(
    modifier = Modifier.padding(start = Spacing24),
    text = text,
    style = TodoAppTypography.caption
      .copy(
        fontWeight = FontWeight.Medium
      )
      .color(
        if (error) {
          ErrorColor
        } else {
          HintColor
        }
      )
  )
}
