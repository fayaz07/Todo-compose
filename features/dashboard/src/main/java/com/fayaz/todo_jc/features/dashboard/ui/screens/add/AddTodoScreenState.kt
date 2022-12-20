package com.fayaz.todo_jc.features.dashboard.ui.screens.add

import com.fayaz.todo_jc.core.base.vm.ViewState
import java.time.DayOfWeek

data class AddTodoScreenState(
  val loading: Boolean,
  val title: String,
  val description: String,
  val recurring: Boolean,
  val frequencyDropDownExpanded: Boolean,
  val selectedFrequency: EventFrequencyEnum,
  val hour: Int,
  val minute: Int,
  val selectedDaysOfWeek: List<DayOfWeek>,
  val dayDropDownExpanded: Boolean,
  val selectedDayOfMonth: Int,
) : ViewState()
