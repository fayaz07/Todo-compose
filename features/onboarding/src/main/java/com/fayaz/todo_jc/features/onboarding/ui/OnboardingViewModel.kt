package com.fayaz.todo_jc.features.onboarding.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fayaz.todo_jc.features.onboarding.domain.CheckLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@HiltViewModel
class OnboardingViewModel @Inject constructor(
  private val checkLoggedInUseCase: CheckLoggedInUseCase
) : ViewModel() {
  private val _ld = MutableLiveData<Boolean>()
  val ld: LiveData<Boolean> = _ld

  fun loggedIn() {
    viewModelScope.launch {
      checkLoggedInUseCase.invoke().first()
      _ld.postValue(true)
    }
  }
}
