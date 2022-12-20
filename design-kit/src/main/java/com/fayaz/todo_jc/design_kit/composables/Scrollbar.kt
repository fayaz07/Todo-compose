package com.fayaz.todo_jc.design_kit.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fayaz.todo_jc.design_kit.theme.ScrollBarColor

// Thanks to https://stackoverflow.com/a/68056586/10152189
fun Modifier.verticalScrollbar(
  state: LazyListState,
  width: Dp = 24.dp
): Modifier = composed {
  val targetAlpha = if (state.isScrollInProgress) 1f else 0f
  val duration = if (state.isScrollInProgress) 150 else 500

  val alpha by animateFloatAsState(
    targetValue = targetAlpha,
    animationSpec = tween(durationMillis = duration)
  )

  drawWithContent {
    drawContent()

    val firstVisibleElementIndex = state.layoutInfo.visibleItemsInfo.firstOrNull()?.index
    val needDrawScrollbar = state.isScrollInProgress || alpha > 0.0f

    // Draw scrollbar if scrolling or if the animation is still running and lazy column has content
    if (needDrawScrollbar && firstVisibleElementIndex != null) {
      val elementHeight = this.size.height / state.layoutInfo.totalItemsCount
      val scrollbarOffsetY = firstVisibleElementIndex * elementHeight
      val scrollbarHeight = state.layoutInfo.visibleItemsInfo.size * elementHeight

      drawRect(
        color = ScrollBarColor,
        topLeft = Offset(this.size.width - width.toPx(), scrollbarOffsetY),
        size = Size(width.toPx(), scrollbarHeight),
        alpha = alpha
      )
    }
  }
}
