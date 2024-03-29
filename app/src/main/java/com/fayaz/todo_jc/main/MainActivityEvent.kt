package com.fayaz.todo_jc.main

import com.fayaz.todo_jc.core.base.vm.ViewEvent

sealed class MainActivityEvent : ViewEvent() {
  data class LoginCheckComplete(val loggedIn: Boolean) : MainActivityEvent()
}
