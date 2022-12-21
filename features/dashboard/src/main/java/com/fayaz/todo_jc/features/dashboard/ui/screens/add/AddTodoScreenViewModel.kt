package com.fayaz.todo_jc.features.dashboard.ui.screens.add

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.fayaz.todo_jc.core.base.vm.StateViewModel
import com.fayaz.todo_jc.core.permissions.DialogAction
import com.fayaz.todo_jc.core.permissions.PermissionCallback
import com.fayaz.todo_jc.core.permissions.PermissionsEnum
import com.fayaz.todo_jc.features.dashboard.ui.activity.DashboardEvent
import com.fayaz.todo_jc.features.dashboard.ui.activity.DashboardViewModel
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.AddTodo
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.DescriptionChanged
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.FrequencyChanged
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.RecurringChanged
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.TitleChanged
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mohammadfayaz.todojc.utils.core.constants.DateTimeConstants.INITIAL_HOUR
import dev.mohammadfayaz.todojc.utils.core.constants.DateTimeConstants.INITIAL_MINUTE
import dev.mohammadfayaz.todojc.utils.core.constants.DateTimeConstants.INITIAL_MONTH
import dev.mohammadfayaz.todojc.utils.core.date.Month
import java.time.DayOfWeek
import javax.inject.Inject
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AddTodoScreenViewModel @Inject constructor(
  private val sharedViewModel: DashboardViewModel
) :
  StateViewModel<AddTodoScreenEvent, AddTodoScreenState>(), PermissionCallback {
  init {
    listenToSharedEvents()
  }

  private fun listenToSharedEvents() {
    viewModelScope.launch {
      sharedViewModel.sharedEvents.receiveAsFlow().collect {
        when (it) {
          is DashboardEvent.PermissionDenied -> {}
          is DashboardEvent.PermissionGranted -> {}
          is DashboardEvent.PermissionPermanentlyDenied -> {}
          else -> {}
        }
      }
    }
  }

  override fun setInitialState(): AddTodoScreenState {
    return AddTodoScreenState(
      loading = false,
      title = "",
      description = "",
      recurring = false,
      selectedFrequency = EventFrequencyEnum.Daily,
      hour = INITIAL_HOUR, minute = INITIAL_MINUTE, selectedDaysOfWeek = emptyList(),
      selectedDayOfMonth = INITIAL_MONTH, selectedMonth = Month.JANUARY,
      showPermissionDialog = false, reqCurrentPermission = PermissionsEnum.Gallery
    )
  }

  override fun dispatcher(event: AddTodoScreenEvent) {
    when (event) {
      AddTodo -> {}
      is TitleChanged -> {
        updateState {
          copy(
            title = event.value
          )
        }
      }
      is DescriptionChanged -> {
        updateState {
          copy(
            description = event.value
          )
        }
      }
      is RecurringChanged -> {
        updateState {
          copy(
            recurring = event.recurring
          )
        }
      }
      is AddTodoScreenEvent.TimePicked -> {
        updateState {
          copy(
            hour = event.hour,
            minute = event.minute
          )
        }
      }
      is AddTodoScreenEvent.SelectedDayOfMonth -> {
        updateState {
          copy(
            selectedDayOfMonth = event.day
          )
        }
      }
      is AddTodoScreenEvent.SelectedMonth -> updateState {
        copy(
          selectedMonth = event.month
        )
      }
      is FrequencyChanged -> handleFrequencyPicked(event)
      is AddTodoScreenEvent.SelectedDayOfWeek -> handleWeekDaysSelection(event)
      is AddTodoScreenEvent.UnSelectedDayOfWeek -> handleWeekDayUnSelection(event)
      is AddTodoScreenEvent.RequestPermission -> {
        Log.d("perm", "add todo view model initiated gallery permission request")
        viewModelScope.launch {
          viewEvents.emit(event)
        }
//        sharedViewModel.dispatcher(
//          DashboardEvent.RequestPermission(
//            PermissionsEnum.Gallery
//          )
//        )
//        updateState {
//          copy(
//            showPermissionDialog = true
//          )
//        }
      }
      is AddTodoScreenEvent.PermissionDialogAction -> {
        when (event.action) {
          DialogAction.DISMISS -> {}
          DialogAction.CONFIRM -> {}
          DialogAction.NEGATIVE -> {}
        }
        updateState {
          copy(
            showPermissionDialog = false
          )
        }
      }
      else -> {}
    }
  }

  private fun handleFrequencyPicked(event: FrequencyChanged) {
    updateState {
      copy(
        selectedFrequency = event.frequency,
        selectedDaysOfWeek = emptyList()
      )
    }
  }

  private fun handleWeekDayUnSelection(event: AddTodoScreenEvent.UnSelectedDayOfWeek) {
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

  private fun handleWeekDaysSelection(event: AddTodoScreenEvent.SelectedDayOfWeek) {
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
      viewEvents.emit(AddTodoScreenEvent.SnackBar("permission granted"))
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
