package com.fayaz.todo_jc.features.dashboard.ui.screens.add

import com.fayaz.todo_jc.core.base.vm.ViewEvent
import com.fayaz.todo_jc.core.permissions.DialogAction
import com.fayaz.todo_jc.core.permissions.PermissionsEnum
import dev.mohammadfayaz.todojc.utils.core.date.Month
import java.time.DayOfWeek

sealed class AddTodoScreenEvent : ViewEvent() {
  object AddTodo : AddTodoScreenEvent()
  data class TitleChanged(val value: String) : AddTodoScreenEvent()
  data class DescriptionChanged(val value: String) : AddTodoScreenEvent()
  data class RecurringChanged(val recurring: Boolean) : AddTodoScreenEvent()
  data class FrequencyChanged(val frequency: EventFrequencyEnum) : AddTodoScreenEvent()
  data class TimePicked(val hour: Int, val minute: Int) : AddTodoScreenEvent()
  data class SelectedDayOfWeek(val day: DayOfWeek) : AddTodoScreenEvent()
  data class UnSelectedDayOfWeek(val day: DayOfWeek) : AddTodoScreenEvent()
  data class SelectedMonth(val month: Month) : AddTodoScreenEvent()
  data class SelectedDayOfMonth(val day: Int) : AddTodoScreenEvent()
  data class RequestPermission(val permission: PermissionsEnum) : AddTodoScreenEvent()
  data class PermissionDialogAction(val action: DialogAction) : AddTodoScreenEvent()
  data class SnackBar(val message: String) : AddTodoScreenEvent()
}
