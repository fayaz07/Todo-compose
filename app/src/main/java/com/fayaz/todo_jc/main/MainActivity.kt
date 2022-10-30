package com.fayaz.todo_jc.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.fayaz.todo_jc.domain.actions.activity.ShowDashboardActivity
import com.fayaz.todo_jc.domain.actions.activity.ShowOnboardingActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  @Inject
  lateinit var showOnboardingActivity: ShowOnboardingActivity

  @Inject
  lateinit var showDashboardActivity: ShowDashboardActivity

  private val viewModel: MainViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    installSplashScreen().apply {
      setKeepOnScreenCondition {
        viewModel.loading.value
      }
    }

    listenToEvents()
    viewModel.checkLogin()
  }

  private fun listenToEvents() {
    viewModel.state.observe(this) {
      when (it) {
        is MainActivityEvent.LoginCheckComplete -> {
          if (it.loggedIn) {
            showDashboardActivity.show(this)
          } else {
            showOnboardingActivity.show(this)
          }
          finish()
        }
      }
    }
  }
}
