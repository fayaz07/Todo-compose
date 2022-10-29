package com.fayaz.todo_jc.domain.data.prefs

import kotlinx.coroutines.flow.Flow

interface PrefsRepo {
  fun isLoggedIn(): Flow<Boolean>

  fun loggedIn()

  fun loggedOut()
}
