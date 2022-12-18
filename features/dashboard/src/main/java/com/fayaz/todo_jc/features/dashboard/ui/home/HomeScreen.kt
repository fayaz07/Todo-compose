package com.fayaz.todo_jc.features.dashboard.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
@Preview
private fun Preview() {
  HomeContent(HomeScreenViewModel(), {})
}

@Composable
fun HomeScreen() {
  val viewModel: HomeScreenViewModel = hiltViewModel()
  val actor = viewModel::dispatcher
  HomeContent(viewModel, actor)
}

@Composable
private fun HomeContent(viewModel: HomeScreenViewModel, actor: (event: HomeScreenEvent) -> Unit) {
  Scaffold(
    floatingActionButton = {
      FloatingActionButton(onClick = {
        actor(HomeScreenEvent.AddTodo)
      }) {
        Icon(
          imageVector = Icons.Default.Add,
          contentDescription = "Icon",
          tint = Color.White
        )
      }
    }
  ) {
    Column(
      modifier = Modifier.padding(it)
    ) {

    }
  }
}
