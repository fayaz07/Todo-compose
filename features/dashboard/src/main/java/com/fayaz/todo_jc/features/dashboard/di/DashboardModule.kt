package com.fayaz.todo_jc.features.dashboard.di

import com.fayaz.todo_jc.domain.actions.activity.ShowDashboardActivity
import com.fayaz.todo_jc.features.dashboard.action.ShowDashboardActivityImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object DashboardModule {
  @Provides
  fun provideShowDashboardActivity(): ShowDashboardActivity = ShowDashboardActivityImpl()
}
