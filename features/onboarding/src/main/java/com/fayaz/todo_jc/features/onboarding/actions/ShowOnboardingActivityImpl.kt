package com.fayaz.todo_jc.features.onboarding.actions

import android.app.Activity
import android.content.Intent
import com.fayaz.todo_jc.core.actions.ShowOnboardingActivity
import com.fayaz.todo_jc.features.onboarding.ui.OnboardingActivity
import javax.inject.Inject

class ShowOnboardingActivityImpl @Inject constructor(): ShowOnboardingActivity {
  override fun show(activity: Activity) {
    activity.startActivity(Intent(activity, OnboardingActivity::class.java))
  }
}
