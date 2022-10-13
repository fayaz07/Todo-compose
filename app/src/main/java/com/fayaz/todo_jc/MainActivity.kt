package com.fayaz.todo_jc

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.fayaz.todo_jc.design_kit.theme.AppTheme
import com.fayaz.todo_jc.features.onboarding.OnboardingActivity

class MainActivity : ComponentActivity() {
  private val viewModel: MainViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)

    installSplashScreen().apply {
      setKeepOnScreenCondition {
        viewModel.isLoading.value
      }
    }
    startActivity(
      Intent(this, OnboardingActivity::class.java)
    )
    finish()

//    setContent {
//      AppTheme {
//        Surface(
//          modifier = Modifier.fillMaxSize(),
//          color = MaterialTheme.colors.background
//        ) {
//          Text(text = "Bonjour Elliot")
//        }
//      }
//    }

  }
}
