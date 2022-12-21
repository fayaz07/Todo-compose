package com.fayaz.todo_jc.design_kit.composables

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.fayaz.todo_jc.core.permissions.PermissionsEnum

@Preview
@Composable
private fun Preview() {
  PermissionDialog(PermissionsEnum.Gallery, {}) {}
}

@Composable
fun PermissionDialog(
  permission: PermissionsEnum,
  onDismiss: () -> Unit,
  onConfirm: () -> Unit,
) {
  AlertDialog(
    onDismissRequest = onDismiss,
    confirmButton = {
      Button(onClick = onConfirm) {
        Text(text = "Open Settings")
      }
    },
    dismissButton = {
      TextButton(onClick = onDismiss) {
        Text(text = "Not Now")
      }
    },
    title = { Text(text = "Missing permission for ${permission.name}") },
    text = {
      Text(
        text = "We would need to access your ${permission.name} to continue using the application"
      )
    },
    properties = DialogProperties(
      dismissOnBackPress = false,
      dismissOnClickOutside = false,
    )
  )
}
