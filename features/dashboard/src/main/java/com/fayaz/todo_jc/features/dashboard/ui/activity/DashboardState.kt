package com.fayaz.todo_jc.features.dashboard.ui.activity

import com.fayaz.todo_jc.core.base.vm.ViewState

data class DashboardState(
  val loading: Boolean = false,
  val text: String
) : ViewState()
