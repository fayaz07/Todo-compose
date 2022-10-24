package com.fayaz.todo_jc.features.dashboard

import com.fayaz.todo_jc.domain.actions.activity.ShowDashboardActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

const val TEST_CONFIG_VALUE = 12

@Module
@InstallIn(SingletonComponent::class)
object DashboardModule {
  @Provides
  fun provideShowDashboardActivity(): ShowDashboardActivity = ShowDashboardActivityImpl()

  @Provides
  @Named("test_config")
  fun getTestConfig() = TEST_CONFIG_VALUE
}
