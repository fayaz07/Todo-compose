package com.fayaz.todo_jc.core.content

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

class ImagePicker(private val onImagePicked: (Uri?) -> Unit) {
  private var picker: ManagedActivityResultLauncher<String, Uri?>? = null

  @Composable
  fun Setup() {
    picker = rememberLauncherForActivityResult(
      ActivityResultContracts.GetContent()
    ) {
      onImagePicked(it)
    }
  }

  fun cleanUp() {
    picker = null
  }
}
