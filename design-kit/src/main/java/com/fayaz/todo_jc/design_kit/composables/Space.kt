package com.fayaz.todo_jc.design_kit.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun Space(space: Dp) {
  Spacer(modifier = Modifier.padding(top = space))
}