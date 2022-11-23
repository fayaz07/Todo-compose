package com.fayaz.todo_jc.features.dashboard.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fayaz.todo_jc.design_kit.theme.AppTheme
import com.fayaz.todo_jc.features.dashboard.ui.activity.DashboardEvent.ValidateText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          Greeting()
        }
      }
    }

    Log.d("test", this::class.java.name)
  }

  @Composable
  fun Greeting() {
    val viewModel: DashboardViewModel = viewModel()
    val state = viewModel.viewState.collectAsState().value

    Column {
      Text(text = "Hello ${viewModel.config} ${state.text}!")
      GetTextField(
        state,
        viewModel::dispatcher
      )
    }
  }

  @Composable
  fun GetTextField(state: DashboardState, actor: (DashboardEvent) -> Unit) {
    OutlinedTextField(value = state.text, onValueChange = {
      actor(ValidateText(it))
    })
  }
}
