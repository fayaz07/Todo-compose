package com.fayaz.todo_jc.features.onboarding.di

import android.app.Activity
import com.fayaz.todo_jc.domain.actions.activity.ShowDashboardActivity
import com.fayaz.todo_jc.domain.data.prefs.PrefsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
  components = [SingletonComponent::class],
  replaces = [OnboardingActivityModule::class]
)
object FakeOnboardingInjects {
  @Provides
  fun provideMockShowDashboardActivity(): ShowDashboardActivity = object :
    ShowDashboardActivity {
    override fun show(activity: Activity) {
      // does nothing
    }
  }

  @Provides
  fun provideFakePrefsRepo(): PrefsRepo = FakePrefsRepo()
}
