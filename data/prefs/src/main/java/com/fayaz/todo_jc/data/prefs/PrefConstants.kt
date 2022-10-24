package com.fayaz.todo_jc.data.prefs

import androidx.datastore.preferences.core.booleanPreferencesKey

internal object PrefConstants {
  const val scope = "prefs_scope"
  const val storeKey = "todo_jc_preferences"
  val loggedIn = booleanPreferencesKey("logged_in")
}
