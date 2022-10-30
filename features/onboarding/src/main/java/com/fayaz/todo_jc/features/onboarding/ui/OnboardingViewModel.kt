package com.fayaz.todo_jc.features.onboarding.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fayaz.todo_jc.domain.data.prefs.PrefsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class OnboardingViewModel @Inject constructor(
  private val prefsRepo: PrefsRepo
) : ViewModel() {
  private val _ld = MutableLiveData<Boolean>()
  val ld: LiveData<Boolean> = _ld

  fun loggedIn() {
    viewModelScope.launch {
      prefsRepo.loggedIn()
      _ld.postValue(true)
    }
  }
}
