package com.fayaz.todo_jc.data.repository.di

import com.fayaz.todo_jc.data.prefs.AppDataStore
import com.fayaz.todo_jc.data.repository.prefs.PrefsRepoImpl
import com.fayaz.todo_jc.domain.data.prefs.PrefsRepo
import com.fayaz.todo_jc.domain.di.AppDispatchers
import com.fayaz.todo_jc.domain.di.TodoAppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
  @Provides
  fun providePrefsRepository(
    appDataStore: AppDataStore,
    @AppDispatchers(TodoAppDispatchers.IO) dispatcher: CoroutineDispatcher
  ): PrefsRepo = PrefsRepoImpl(appDataStore, dispatcher)
}
