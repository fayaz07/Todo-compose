package com.fayaz.todo_jc.features.dashboard.ui.home

import com.fayaz.todo_jc.core.base.vm.ViewState

data class HomeScreenState(
  val loading: Boolean = false,
  val text: String
) : ViewState()
