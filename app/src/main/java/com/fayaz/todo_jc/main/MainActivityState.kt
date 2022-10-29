package com.fayaz.todo_jc.main

import com.fayaz.todo_jc.core.base.vm.ViewState

data class MainActivityState(
  val userLoggedIn: Boolean = false
) : ViewState()
