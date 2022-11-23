package com.fayaz.todo_jc.features.onboarding.di

import com.fayaz.todo_jc.domain.data.prefs.PrefsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakePrefsRepo : PrefsRepo {
  override fun isLoggedIn(): Flow<Boolean> {
    return flowOf(true)
  }

  override fun loggedIn() {

  }

  override fun loggedOut() {

  }
}
