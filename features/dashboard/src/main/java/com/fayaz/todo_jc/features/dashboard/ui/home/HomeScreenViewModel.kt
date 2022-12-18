package com.fayaz.todo_jc.features.dashboard.ui.home

import com.fayaz.todo_jc.core.base.vm.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() :
  StateViewModel<HomeScreenEvent, HomeScreenState>() {
  override fun setInitialState(): HomeScreenState {
    return HomeScreenState(
      loading = false,
      text = ""
    )
  }

  override fun dispatcher(event: HomeScreenEvent) {
    when (event) {
      HomeScreenEvent.AddTodo -> {}
    }
  }
}
