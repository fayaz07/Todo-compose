package com.fayaz.todo_jc.features.dashboard.ui.screens.add

import com.fayaz.todo_jc.core.base.vm.ViewEvent

sealed class AddTodoScreenEvent : ViewEvent() {
  object AddTodo : AddTodoScreenEvent()
  data class TitleChanged(val value: String) : AddTodoScreenEvent()
  data class DescriptionChanged(val value: String) : AddTodoScreenEvent()
}
