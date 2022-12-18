package com.fayaz.todo_jc.features.dashboard.ui.screens.add

import com.fayaz.todo_jc.core.base.vm.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTodoScreenViewModel @Inject constructor() :
  StateViewModel<AddTodoScreenEvent, AddTodoScreenState>() {
  override fun setInitialState(): AddTodoScreenState {
    return AddTodoScreenState(false, "", "")
  }

  override fun dispatcher(event: AddTodoScreenEvent) {
    when (event) {
      AddTodoScreenEvent.AddTodo -> {}
      is AddTodoScreenEvent.TitleChanged -> {
        updateState {
          copy(
            title = event.value
          )
        }
      }

      is AddTodoScreenEvent.DescriptionChanged -> {
        updateState {
          copy(
            description = event.value
          )
        }
      }
    }
  }
}
