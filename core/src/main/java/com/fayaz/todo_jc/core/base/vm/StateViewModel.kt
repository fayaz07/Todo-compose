package com.fayaz.todo_jc.core.base.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class StateViewModel<UiEvent: ViewEvent, UiState: ViewState>: ViewModel() {
  private val initialState: UiState by lazy { setInitialState() }
  abstract fun setInitialState(): UiState

  private val _uiState = MutableStateFlow(initialState)
  val viewState = _uiState.asStateFlow()

  val viewEvents = MutableSharedFlow<UiEvent>()

  abstract fun dispatcher(event: UiEvent)

  fun updateState(reducer: UiState.() -> UiState) {
    val updatedState = viewState.value.reducer()
    _uiState.value = updatedState
  }
}
