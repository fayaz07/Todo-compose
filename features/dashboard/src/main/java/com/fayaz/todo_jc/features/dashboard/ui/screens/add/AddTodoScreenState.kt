package com.fayaz.todo_jc.features.dashboard.ui.screens.add

import com.fayaz.todo_jc.core.base.vm.ViewState
import com.fayaz.todo_jc.features.dashboard.utils.Month
import java.time.DayOfWeek

data class AddTodoScreenState(
  val loading: Boolean,
  val title: String,
  val description: String,
  val recurring: Boolean,
  val selectedFrequency: EventFrequencyEnum,
  val hour: Int,
  val minute: Int,
  val selectedDaysOfWeek: List<DayOfWeek>,
  val selectedDayOfMonth: Int,
  val selectedMonth: Month
) : ViewState()
