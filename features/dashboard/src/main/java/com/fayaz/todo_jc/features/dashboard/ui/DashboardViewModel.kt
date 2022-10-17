package com.fayaz.todo_jc.features.dashboard.ui

import com.fayaz.todo_jc.core.logger.base.StateViewModel
import com.fayaz.todo_jc.features.dashboard.ui.DashboardEvent
import com.fayaz.todo_jc.features.dashboard.ui.DashboardState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DashboardViewModel @Inject constructor(
  @Named("test_config") val config: Int
) : StateViewModel<DashboardEvent, DashboardState>() {
  override fun setInitialState(): DashboardState {
    return DashboardState(
      loading = false,
      text = ""
    )
  }

  override fun dispatcher(event: DashboardEvent) {
    when (event) {
      DashboardEvent.Submit -> {

      }
      is DashboardEvent.ValidateText -> {
        updateState {
          copy(
            text = event.text
          )
        }
      }
    }
  }
}
