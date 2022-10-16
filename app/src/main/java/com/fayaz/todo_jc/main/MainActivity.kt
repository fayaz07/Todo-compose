package com.fayaz.todo_jc.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.fayaz.todo_jc.design_kit.theme.AppTheme

class MainActivity : ComponentActivity() {
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

    setContent {
      AppTheme {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          Text(text = "Bonjour Elliot", style = MaterialTheme.typography.h4)
        }
      }
    }

  }
}
