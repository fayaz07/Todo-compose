package com.fayaz.todo_jc.features.onboarding

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold

class OnboardingActivity : ComponentActivity() {
  @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      Scaffold {
        OnboardingPage(backgroundColor = MaterialTheme.colors.primary)
      }
    }
  }
}
