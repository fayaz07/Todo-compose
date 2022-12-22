package com.fayaz.todo_jc.design_kit.composables

import android.net.Uri
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun AttachmentComposable(
  uri: Uri,
  modifier: Modifier = Modifier,
  size: Dp = 100.dp
) {
  AsyncImage(
    modifier = modifier
      .size(size)
      .clip(RoundedCornerShape(4.dp)),
    model = uri,
    contentDescription = null
  )
}
