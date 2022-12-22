package com.fayaz.todo_jc.design_kit.composables

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalMinimumTouchTargetEnforcement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fayaz.todo_jc.design_kit.theme.IconTintColor
import com.fayaz.todo_jc.design_kit.theme.Spacing4
import com.fayaz.todo_jc.design_kit.theme.TextColorLight

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AttachmentComposable(
  uri: Uri,
  modifier: Modifier = Modifier,
  size: Dp = 100.dp,
  onRemove: () -> Unit
) {
  Box(
    modifier = modifier
      .size(size)
      .clip(RoundedCornerShape(4.dp)),
  ) {
    AsyncImage(
      model = uri,
      contentDescription = null
    )
    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
      IconButton(
        modifier = Modifier
          .align(Alignment.TopEnd)
          .clip(CircleShape)
          .background(IconTintColor),
        onClick = onRemove
      ) {
        Icon(
          modifier = Modifier.padding(Spacing4),
          imageVector = Icons.Filled.Clear,
          contentDescription = "Clear image button",
          tint = TextColorLight
        )
      }
    }
  }
}

@Preview
@Composable
private fun Preview() {
  val twLogo =
    "https://www.thoughtworks.com/etc.clientlibs/thoughtworks/clientlibs/clientlib-site/resources/images/thoughtworks-logo.svg"
  AttachmentComposable(uri = Uri.parse(twLogo)) {}
}
