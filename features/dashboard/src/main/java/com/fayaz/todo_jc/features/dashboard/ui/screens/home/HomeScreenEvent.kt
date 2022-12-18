package com.fayaz.todo_jc.features.dashboard.ui.screens.home

import com.fayaz.todo_jc.core.base.vm.ViewEvent

sealed class HomeScreenEvent : ViewEvent() {
  object AddTodo: HomeScreenEvent()
}
