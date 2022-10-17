package com.fayaz.todo_jc.core.logger.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

abstract class StateViewModel<UiEvent: ViewEvent, UiState: ViewState>: ViewModel() {
  private val initialState: UiState by lazy { setInitialState() }
  abstract fun setInitialState(): UiState

  private val _uiState: MutableState<UiState> = mutableStateOf(initialState)
  val viewState: State<UiState> = _uiState

  abstract fun dispatcher(event: UiEvent)
}
