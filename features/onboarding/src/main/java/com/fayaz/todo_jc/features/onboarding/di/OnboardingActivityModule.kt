package com.fayaz.todo_jc.features.onboarding.di

import com.fayaz.todo_jc.core.logger.actions.ShowOnboardingActivity
import com.fayaz.todo_jc.features.onboarding.actions.ShowOnboardingActivityImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object OnboardingActivityModule {
  @ActivityScoped
  @Provides
  fun provideShowOnboardingActivityAction(): ShowOnboardingActivity = ShowOnboardingActivityImpl()
}
