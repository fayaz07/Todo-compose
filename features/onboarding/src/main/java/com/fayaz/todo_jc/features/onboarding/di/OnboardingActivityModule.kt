package com.fayaz.todo_jc.features.onboarding.di

import com.fayaz.todo_jc.domain.actions.activity.ShowOnboardingActivity
import com.fayaz.todo_jc.features.onboarding.actions.ShowOnboardingActivityImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object OnboardingActivityModule {

  @Provides
  fun provideShowOnboardingActivityAction(): ShowOnboardingActivity = ShowOnboardingActivityImpl()
}
