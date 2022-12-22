package com.fayaz.todo_jc.features.dashboard.ui.screens.add

import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.fayaz.todo_jc.core.base.vm.StateViewModel
import com.fayaz.todo_jc.core.permissions.PermissionCallback
import com.fayaz.todo_jc.core.permissions.PermissionsEnum
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.AddTodo
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.AttachmentEvent
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.FormEvent
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.PermissionDialogAction
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.RequestPermission
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.SnackBar
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mohammadfayaz.todojc.utils.core.constants.DateTimeConstants.INITIAL_HOUR
import dev.mohammadfayaz.todojc.utils.core.constants.DateTimeConstants.INITIAL_MINUTE
import dev.mohammadfayaz.todojc.utils.core.constants.DateTimeConstants.INITIAL_MONTH
import dev.mohammadfayaz.todojc.utils.core.date.Month
import java.time.DayOfWeek
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AddTodoScreenViewModel @Inject constructor() :
  StateViewModel<AddTodoScreenEvent, AddTodoScreenState>(), PermissionCallback {

  private var onPermissionGranted: (suspend () -> Unit)? = null

  override fun setInitialState(): AddTodoScreenState {
    return AddTodoScreenState(
      loading = false,
      title = "",
      description = "",
      recurring = false,
      selectedFrequency = EventFrequencyEnum.Daily,
      hour = INITIAL_HOUR, minute = INITIAL_MINUTE, selectedDaysOfWeek = emptyList(),
      selectedDayOfMonth = INITIAL_MONTH, selectedMonth = Month.JANUARY,
      showPermissionDialog = false, reqCurrentPermission = PermissionsEnum.Gallery,
      attachments = emptyList()
    )
  }

  override fun dispatcher(event: AddTodoScreenEvent) {
    when (event) {
      AddTodo -> {}
      is FormEvent -> handleFormEvent(event)
      is RequestPermission -> {
        Log.d("perm", "add todo view model initiated gallery permission request")
        viewModelScope.launch {
          viewEvents.emit(event)
        }
      }
      is PermissionDialogAction -> {
        updateState {
          copy(
            showPermissionDialog = false
          )
        }
      }
      is AttachmentEvent -> handleAttachmentEvent(event)
      else -> {}
    }
  }

  private fun handleAttachmentEvent(event: AttachmentEvent) {
    when (event) {
      is AttachmentEvent.ImagePicked -> viewModelScope.launch {
        if (event.uri == null) {
          viewEvents.emit(SnackBar("No image picked"))
          return@launch
        }
        val updatedList = mutableListOf<Uri>()
        updatedList.addAll(viewState.value.attachments)
        updatedList.add(event.uri)
        updateState {
          copy(
            attachments = updatedList
          )
        }
      }
      AttachmentEvent.LaunchPicker -> viewModelScope.launch {
        viewEvents.emit(RequestPermission(PermissionsEnum.Gallery))
        onPermissionGranted = {
          viewEvents.emit(AttachmentEvent.LaunchPicker)
        }
      }
    }
  }

  private fun handleFormEvent(event: FormEvent) {
    when (event) {
      is FormEvent.TitleChanged -> updateState {
        copy(
          title = event.value
        )
      }
      is FormEvent.DescriptionChanged -> updateState {
        copy(
          description = event.value
        )
      }
      is FormEvent.RecurringChanged -> updateState {
        copy(
          recurring = event.recurring
        )
      }
      is FormEvent.TimePicked -> updateState {
        copy(
          hour = event.hour,
          minute = event.minute
        )
      }
      is FormEvent.SelectedDayOfMonth -> updateState {
        copy(
          selectedDayOfMonth = event.day
        )
      }
      is FormEvent.SelectedMonth -> updateState {
        copy(
          selectedMonth = event.month
        )
      }
      is FormEvent.FrequencyChanged -> handleFrequencyPicked(event)
      is FormEvent.SelectedDayOfWeek -> handleWeekDaysSelection(event)
      is FormEvent.UnSelectedDayOfWeek -> handleWeekDayUnSelection(event)
    }
  }

  private fun handleFrequencyPicked(event: FormEvent.FrequencyChanged) {
    updateState {
      copy(
        selectedFrequency = event.frequency,
        selectedDaysOfWeek = emptyList()
      )
    }
  }

  private fun handleWeekDayUnSelection(event: FormEvent.UnSelectedDayOfWeek) {
    val state = viewState.value
    val updatedList = mutableListOf<DayOfWeek>()
    if (state.selectedFrequency == EventFrequencyEnum.SpecificDays) {
      updatedList.addAll(state.selectedDaysOfWeek)
      updatedList.remove(event.day)
    }
    updateState {
      copy(
        selectedDaysOfWeek = updatedList
      )
    }
  }

  private fun handleWeekDaysSelection(event: FormEvent.SelectedDayOfWeek) {
    val state = viewState.value
    val updatedList = mutableListOf<DayOfWeek>()
    if (state.selectedFrequency == EventFrequencyEnum.Weekly) {
      updatedList.add(event.day)
    } else {
      updatedList.addAll(state.selectedDaysOfWeek)
      updatedList.add(event.day)
    }
    updateState {
      copy(
        selectedDaysOfWeek = updatedList
      )
    }
  }

  override fun onGranted(permission: PermissionsEnum) {
    viewModelScope.launch {
      viewEvents.emit(SnackBar("permission granted"))
      onPermissionGranted?.invoke()
      onPermissionGranted = null
    }
  }

  override fun onDenied(permission: PermissionsEnum) {
    updateState {
      copy(
        showPermissionDialog = true
      )
    }
  }

  override fun onPermanentlyDenied(permission: PermissionsEnum) {
    updateState {
      copy(
        showPermissionDialog = true
      )
    }
  }
}
