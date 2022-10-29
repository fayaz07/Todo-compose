package com.fayaz.todo_jc.data.prefs

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class AppDataStore @Inject constructor(
  private val dataStore: DataStore<Preferences>,
  @Named(PrefConstants.scope) private val scope: CoroutineScope
) {
  suspend fun updateLogin(loggedIn: Boolean) {
    dataStore.edit {
      it[PrefConstants.loggedIn] = loggedIn
    }
  }

  fun isLoggedIn(): Flow<Boolean> {
    return flow {
      dataStore.data.map {
        it[PrefConstants.loggedIn] ?: false
      }
    }
  }

  suspend fun clear() {
    dataStore.edit {
      Log.d("prefs", it.asMap().toString())
      it.clear()
      Log.d("prefs", it.asMap().toString())
    }
  }
}
