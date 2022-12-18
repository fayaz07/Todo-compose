package com.fayaz.todo_jc.features.dashboard.ui.screens.add

import com.fayaz.todo_jc.core.base.vm.ViewState

data class AddTodoScreenState(
  val loading: Boolean,
  val title: String,
  val description: String,
  val recurring: Boolean,
  val frequencyDropDownExpanded: Boolean,
  val selectedFrequency: EventFrequencyEnum,
  val hour: Int,
  val minute: Int
) : ViewState()
