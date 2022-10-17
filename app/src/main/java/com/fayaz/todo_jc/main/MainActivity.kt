package com.fayaz.todo_jc.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.fayaz.todo_jc.core.logger.actions.ShowOnboardingActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  @Inject
  lateinit var showOnboardingActivity: ShowOnboardingActivity

  private val viewModel: MainViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    installSplashScreen().apply {
      setKeepOnScreenCondition {
        viewModel.isLoading.value
      }
    }

//    startActivity(
//      Intent(this, OnboardingActivity::class.java)
//    )
//    finish()

//    showOnboardingActivity.show(this)

//    setContent {
//      AppTheme {
//        Box(
//          modifier = Modifier.fillMaxSize(),
//          contentAlignment = Alignment.Center
//        ) {
//          Text(text = "Bonjour Elliot", style = MaterialTheme.typography.h4)
//        }
//      }
//    }

  }
}
