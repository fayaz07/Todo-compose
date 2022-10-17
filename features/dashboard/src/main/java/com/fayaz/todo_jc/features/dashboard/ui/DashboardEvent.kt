package com.fayaz.todo_jc.features.dashboard.ui

import com.fayaz.todo_jc.core.logger.base.ViewEvent

sealed class DashboardEvent : ViewEvent() {
  object Submit : DashboardEvent()
  data class ValidateText(val text: String) : DashboardEvent()
}
