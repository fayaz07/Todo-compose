package com.fayaz.todo_jc.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.fayaz.todo_jc.core.base.vm.StateViewModel
import com.fayaz.todo_jc.data.usecase.CheckLoggedInUseCase
import com.fayaz.todo_jc.main.MainActivityEvent.LoginCheckComplete
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
  private val checkLoginUseCase: CheckLoggedInUseCase
) : StateViewModel<MainActivityEvent, MainActivityState>() {

  private val _isLoading = MutableStateFlow(true)
  val isLoading = _isLoading.asStateFlow()

  override fun setInitialState(): MainActivityState {
    return MainActivityState(userLoggedIn = false)
  }

  private fun checkLogin() {
    Log.d("ttt",  " checking login ")
    viewModelScope.launch {
      val loggedIn = checkLoginUseCase.once()
      updateState { copy(userLoggedIn = loggedIn) }
      _isLoading.emit(false)
      viewEvents.emit(LoginCheckComplete)
      Log.d("ttt",  " login check complete ${viewState.value}")
    }
  }

  override fun dispatcher(event: MainActivityEvent) {
    when (event) {
      MainActivityEvent.CheckUserLoginStatus -> checkLogin()
      else -> {}
    }
  }
}
