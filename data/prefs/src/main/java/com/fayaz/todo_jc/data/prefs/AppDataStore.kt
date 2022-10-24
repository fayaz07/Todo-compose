package com.fayaz.todo_jc.data.prefs

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import java.util.stream.Collectors
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.toCollection

class AppDataStore @Inject constructor(
  private val dataStore: DataStore<Preferences>,
  @Named(PrefConstants.scope) private val scope: CoroutineScope
) {
  suspend fun updateLogin(loggedIn: Boolean) {
    dataStore.edit {
      it[PrefConstants.loggedIn] = loggedIn
    }
  }

  suspend fun isLoggedIn(): Boolean {
    return dataStore.data.single()[PrefConstants.loggedIn] ?: false
  }

  suspend fun clear() {
    dataStore.edit {
      Log.d("prefs", it.asMap().toString())
      it.clear()
      Log.d("prefs", it.asMap().toString())
    }
  }
}
