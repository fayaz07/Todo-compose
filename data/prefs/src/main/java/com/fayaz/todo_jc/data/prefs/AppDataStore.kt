package com.fayaz.todo_jc.data.prefs

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDataStore @Inject constructor(@ApplicationContext val context: Context) {
  private val Context.dataStore by preferencesDataStore(
    name = PrefConstants.storeKey,
  )

  suspend fun updateLogin(loggedIn: Boolean) {
    context.dataStore.edit {
      it[PrefConstants.loggedIn] = loggedIn
    }
  }

  fun isLoggedIn(): Flow<Boolean> {
    return context.dataStore.data.map {
      it[PrefConstants.loggedIn] ?: false
    }
  }

  suspend fun clear() {
    context.dataStore.edit {
      Log.d("prefs", it.asMap().toString())
      it.clear()
      Log.d("prefs", it.asMap().toString())
    }
  }
}
