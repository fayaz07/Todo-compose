package com.fayaz.todo_jc.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object TodoAppDispatchersModule {
  @Provides
  @AppDispatchers(TodoAppDispatchers.IO)
  fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}
