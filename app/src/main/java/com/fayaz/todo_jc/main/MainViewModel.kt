package com.fayaz.todo_jc.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

const val SLIGHT_DELAY = 3000L

class MainViewModel: ViewModel() {
  private val _isLoading = MutableStateFlow(true)
  val isLoading = _isLoading.asStateFlow()

  init {
    checkLogin()
  }

  private fun checkLogin() {
    viewModelScope.launch {
      delay(SLIGHT_DELAY)
      _isLoading.emit(false)
    }
  }
}
