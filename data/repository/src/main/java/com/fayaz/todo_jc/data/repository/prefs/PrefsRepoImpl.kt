package com.fayaz.todo_jc.data.repository.prefs

import com.fayaz.todo_jc.data.prefs.AppDataStore
import com.fayaz.todo_jc.domain.data.prefs.PrefsRepo
import com.fayaz.todo_jc.domain.di.AppDispatchers
import com.fayaz.todo_jc.domain.di.TodoAppDispatchers
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PrefsRepoImpl @Inject constructor(
  private val appDataStore: AppDataStore,
  @AppDispatchers(TodoAppDispatchers.IO) private val coroutineDispatcher: CoroutineDispatcher
) : PrefsRepo {
  override fun isLoggedIn(): Flow<Boolean> {
    return appDataStore.isLoggedIn()
  }

  override fun loggedIn() {
    CoroutineScope(coroutineDispatcher).launch {
      appDataStore.updateLogin(true)
    }
  }

  override fun loggedOut() {
    CoroutineScope(coroutineDispatcher).launch {
      appDataStore.updateLogin(false)
    }
  }
}
