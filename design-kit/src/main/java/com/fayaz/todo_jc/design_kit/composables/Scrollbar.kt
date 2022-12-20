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
import dev.mohammadfayaz.todojc.utils.core.ui.ScreenConstants.DEF_SCROLLBAR_WIDTH
import dev.mohammadfayaz.todojc.utils.core.ui.ScreenConstants.DURATION_SCROLL_IN_PROGRESS
import dev.mohammadfayaz.todojc.utils.core.ui.ScreenConstants.DURATION_SCROLL_NOT_IN_PROGRESS
import dev.mohammadfayaz.todojc.utils.core.ui.ScreenConstants.TARGET_ALPHA_SCROLL_IN_PROGRESS
import dev.mohammadfayaz.todojc.utils.core.ui.ScreenConstants.TARGET_ALPHA_SCROLL_NOT_IN_PROGRESS
import dev.mohammadfayaz.todojc.utils.core.ui.ScreenConstants.ZERO_FLOAT

// Thanks to https://stackoverflow.com/a/68056586/10152189
fun Modifier.verticalScrollbar(
  state: LazyListState,
  width: Dp = DEF_SCROLLBAR_WIDTH.dp
): Modifier = composed {
  val targetAlpha =
    if (state.isScrollInProgress) {
      TARGET_ALPHA_SCROLL_IN_PROGRESS
    } else {
      TARGET_ALPHA_SCROLL_NOT_IN_PROGRESS
    }
  val duration = if (state.isScrollInProgress) {
    DURATION_SCROLL_IN_PROGRESS
  } else {
    DURATION_SCROLL_NOT_IN_PROGRESS
  }

  val alpha by animateFloatAsState(
    targetValue = targetAlpha,
    animationSpec = tween(durationMillis = duration)
  )

  drawWithContent {
    drawContent()

    val firstVisibleElementIndex = state.layoutInfo.visibleItemsInfo.firstOrNull()?.index
    val needDrawScrollbar = state.isScrollInProgress || alpha > ZERO_FLOAT

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

