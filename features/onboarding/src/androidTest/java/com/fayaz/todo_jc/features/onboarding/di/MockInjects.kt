package com.fayaz.todo_jc.features.onboarding.di

import android.app.Activity
import com.fayaz.todo_jc.core.actions.ShowDashboardActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [OnboardingActivityModule::class])
object MockInjects {
  @Provides
  fun provideMockShowDashboardActivity(): ShowDashboardActivity = object : ShowDashboardActivity {
    override fun show(activity: Activity) {
      // does nothing
    }
  }
}
