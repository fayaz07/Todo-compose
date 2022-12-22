package com.fayaz.todo_jc.features.dashboard.ui.screens.add

import android.net.Uri
import com.fayaz.todo_jc.core.base.vm.ViewEvent
import com.fayaz.todo_jc.core.permissions.DialogAction
import com.fayaz.todo_jc.core.permissions.PermissionsEnum
import dev.mohammadfayaz.todojc.utils.core.date.Month
import java.time.DayOfWeek

sealed class AddTodoScreenEvent : ViewEvent() {
  object AddTodo : AddTodoScreenEvent()
  sealed class FormEvent : AddTodoScreenEvent() {
    data class TitleChanged(val value: String) : FormEvent()
    data class DescriptionChanged(val value: String) : FormEvent()
    data class RecurringChanged(val recurring: Boolean) : FormEvent()
    data class FrequencyChanged(val frequency: EventFrequencyEnum) : FormEvent()
    data class TimePicked(val hour: Int, val minute: Int) : FormEvent()
    data class SelectedDayOfWeek(val day: DayOfWeek) : FormEvent()
    data class UnSelectedDayOfWeek(val day: DayOfWeek) : FormEvent()
    data class SelectedMonth(val month: Month) : FormEvent()
    data class SelectedDayOfMonth(val day: Int) : FormEvent()
  }

  sealed class PermissionEvent : AddTodoScreenEvent()
  data class RequestPermission(val permission: PermissionsEnum) : AddTodoScreenEvent()
  data class PermissionDialogAction(val action: DialogAction) : AddTodoScreenEvent()
  data class SnackBar(val message: String) : AddTodoScreenEvent()

  sealed class AttachmentEvent : AddTodoScreenEvent() {
    object LaunchPicker : AttachmentEvent()
    data class ImagePicked(val uri: Uri?) : AttachmentEvent()
    data class Remove(val index: Int) : AttachmentEvent()
  }
}
