package com.fayaz.todo_jc.features.dashboard.ui.screens.add

import android.net.Uri
import com.fayaz.todo_jc.core.base.vm.ViewState
import com.fayaz.todo_jc.core.permissions.PermissionsEnum
import dev.mohammadfayaz.todojc.utils.core.date.Month
import java.time.DayOfWeek

data class AddTodoScreenState(
  val loading: Boolean,
  val title: String,
  val titleError: String = "",
  val description: String,
  val recurring: Boolean,
  val selectedFrequency: EventFrequencyEnum,
  val hour: Int,
  val minute: Int,
  val selectedDaysOfWeek: List<DayOfWeek>,
  val selectedDayOfMonth: Int,
  val selectedMonth: Month,
  val showPermissionDialog: Boolean,
  val reqCurrentPermission: PermissionsEnum? = null,
  val attachments: List<Uri>
) : ViewState()
