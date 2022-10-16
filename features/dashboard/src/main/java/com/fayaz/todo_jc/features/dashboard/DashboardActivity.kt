package com.fayaz.todo_jc.features.dashboard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fayaz.todo_jc.design_kit.theme.AppTheme

class DashboardActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          Greeting("Android")
        }
      }
    }

    Log.d("test", this::class.java.name)
  }

  @Composable
  fun Greeting(name: String) {
    Text(text = "Hello $name!")
  }
}
