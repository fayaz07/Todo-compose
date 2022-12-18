package com.fayaz.todo_jc.features.dashboard.ui.screens.add

import androidx.lifecycle.viewModelScope
import com.fayaz.todo_jc.core.base.vm.StateViewModel
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.AddTodo
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.DescriptionChanged
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.FrequencyChanged
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.RecurringChanged
import com.fayaz.todo_jc.features.dashboard.ui.screens.add.AddTodoScreenEvent.TitleChanged
import dagger.hilt.android.lifecycle.HiltViewModel
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
      selectedFrequency = EventFrequencyEnum.Daily
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
      is FrequencyChanged -> {
        updateState {
          copy(
            selectedFrequency = event.frequency
          )
        }
      }
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
