package com.fayaz.todo_jc.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
) : ViewModel() {

  private val _isLoading = MutableStateFlow<Boolean>(true)
  val loading = _isLoading.asStateFlow()

  private val _state = MutableLiveData<MainActivityEvent>()
  val state: LiveData<MainActivityEvent> = _state

  fun checkLogin() {
    viewModelScope.launch {
      val loggedIn = checkLoginUseCase.once()
      _isLoading.emit(false)
      _state.postValue(LoginCheckComplete(loggedIn))
    }
  }
}
