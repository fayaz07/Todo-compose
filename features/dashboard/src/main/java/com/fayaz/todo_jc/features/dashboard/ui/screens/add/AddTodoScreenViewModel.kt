package com.fayaz.todo_jc.features.dashboard.ui.screens.add

import androidx.lifecycle.viewModelScope
import com.fayaz.todo_jc.core.base.vm.StateViewModel
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.AddTodo
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.DescriptionChanged
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.FrequencyChanged
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.RecurringChanged
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.TitleChanged
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.DayOfWeek
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AddTodoScreenViewModel @Inject constructor() :
  StateViewModel<AddTodoScreenEvent, AddTodoScreenState>() {
  override fun setInitialState(): AddTodoScreenState {
    return AddTodoScreenState(
      loading = false,
      title = "",
      description = "",
      recurring = false,
      frequencyDropDownExpanded = false,
      selectedFrequency = EventFrequencyEnum.Daily,
      hour = 0, minute = 0, selectedDaysOfWeek = emptyList(),
      dayDropDownExpanded = false,
      selectedDayOfMonth = 1
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
      is AddTodoScreenEvent.FrequencyDropDownExpanded -> handleFrequencyDropdownEvent(event)
      is FrequencyChanged -> handleFrequencyPicked(event)
      is AddTodoScreenEvent.TimePicked -> {
        updateState {
          copy(
            hour = event.hour,
            minute = event.minute
          )
        }
      }
      is AddTodoScreenEvent.SelectedDayOfWeek -> handleWeekDaysSelection(event)
      is AddTodoScreenEvent.UnSelectedDayOfWeek -> handleWeekDayUnSelection(event)
      is AddTodoScreenEvent.SelectedDayOfMonth -> {
        updateState {
          copy(
            selectedDayOfMonth = event.day
          )
        }
      }
      is AddTodoScreenEvent.DayOfMonthDropDownExpanded -> {
        updateState {
          copy(
            dayDropDownExpanded = event.expanded
          )
        }
      }
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

  private fun handleFrequencyDropdownEvent(
    event: AddTodoScreenEvent.FrequencyDropDownExpanded
  ) {
    viewModelScope.launch {
      updateState {
        copy(
          frequencyDropDownExpanded = event.expanded
        )
      }
    }
  }
}
