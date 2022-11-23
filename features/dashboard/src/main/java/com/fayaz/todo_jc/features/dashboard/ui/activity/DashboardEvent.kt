package com.fayaz.todo_jc.features.dashboard.ui.activity

import com.fayaz.todo_jc.core.base.vm.ViewEvent

sealed class DashboardEvent : ViewEvent() {
  object Submit : DashboardEvent()
  data class ValidateText(val text: String) : DashboardEvent()
}
