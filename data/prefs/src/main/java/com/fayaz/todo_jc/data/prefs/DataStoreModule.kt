package com.fayaz.todo_jc.data.prefs

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
  @Singleton
  @Provides
  fun provideDataStore(@ApplicationContext context: Context): AppDataStore {
    return AppDataStore(context = context)
  }
}
