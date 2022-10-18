package com.fayaz.todo_jc.features.dashboard.ui

import com.fayaz.todo_jc.core.base.ViewState

data class DashboardState(
  val loading: Boolean = false,
  val text: String
) : ViewState()
