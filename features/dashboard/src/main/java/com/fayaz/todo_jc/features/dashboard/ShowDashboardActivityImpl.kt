package com.fayaz.todo_jc.features.dashboard

import android.app.Activity
import android.content.Intent
import com.fayaz.todo_jc.domain.actions.activity.ShowDashboardActivity
import com.fayaz.todo_jc.features.dashboard.ui.activity.DashboardActivity
import javax.inject.Inject

class ShowDashboardActivityImpl @Inject constructor() : ShowDashboardActivity {
  override fun show(activity: Activity) {
    activity.startActivity(Intent(activity, DashboardActivity::class.java))
  }
}
