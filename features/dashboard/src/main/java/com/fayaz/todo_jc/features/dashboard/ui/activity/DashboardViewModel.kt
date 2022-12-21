package com.fayaz.todo_jc.features.dashboard.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class DashboardViewModel @Inject constructor(): ViewModel() {

  val events = Channel<DashboardEvent>()
  val sharedEvents = Channel<DashboardEvent>()

  fun dispatcher(event: DashboardEvent) {
    when (event) {
      is DashboardEvent.RequestPermission -> viewModelScope.launch {
        events.send(event)
      }
      else -> viewModelScope.launch {
        sharedEvents.send(event)
      }
    }
  }
}
