package com.fayaz.todo_jc.data.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

  private val Context.dataStore by preferencesDataStore(
    name = PrefConstants.storeKey,
  )

  @Provides
  @Named(PrefConstants.scope)
  internal fun provideDataStoreScope() = Dispatchers.IO

  @Provides
  internal fun provideTodoAppDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
    context.dataStore
}
