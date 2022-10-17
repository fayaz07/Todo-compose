package com.fayaz.todo_jc.features.dashboard

import com.fayaz.todo_jc.core.logger.actions.ShowDashboardActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DashboardModule {
  @Provides
  fun provideShowDashboardActivity(): ShowDashboardActivity = ShowDashboardActivityImpl()
}
