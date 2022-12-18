package com.fayaz.todo_jc.features.dashboard.ui.screens.add

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.fayaz.todo_jc.design_kit.composables.AppOutlinedTextField
import com.fayaz.todo_jc.design_kit.composables.Space
import com.fayaz.todo_jc.design_kit.theme.Spacing16
import com.fayaz.todo_jc.design_kit.theme.Spacing24

@Composable
@Preview
private fun Preview() {
  Content(AddTodoScreenState(false, "", "")) {}
}

@Composable
fun AddTodoScreen() {
  val viewModel: AddTodoScreenViewModel = hiltViewModel()
  val state = viewModel.viewState.collectAsState().value
  val actor = viewModel::dispatcher

  Content(state, actor)
}

@Composable
private fun Content(
  state: AddTodoScreenState,
  actor: (event: AddTodoScreenEvent) -> Unit
) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(text = "Add Todo") },
        navigationIcon = {
          IconButton(onClick = { }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow back")
          }
        },
      )
    }
  ) {
    Body(it, state, actor)
  }
}

@Composable
private fun Body(
  paddingValues: PaddingValues,
  state: AddTodoScreenState,
  actor: (event: AddTodoScreenEvent) -> Unit
) {
  Column(
    modifier = Modifier
      .padding(paddingValues)
      .padding(top = Spacing24)
  ) {
    TitleTextField(state, actor)
    Space(Spacing16)
    DescriptionTextField(state, actor)
  }
}

@Composable
private fun TitleTextField(
  state: AddTodoScreenState,
  actor: (event: AddTodoScreenEvent) -> Unit
) {
  AppOutlinedTextField(
    title = "Title",
    hint = "a sweet and short label!",
    value = state.title,
    onValueChange = {
      actor(AddTodoScreenEvent.TitleChanged(it))
    },
    trailingIcon = {
      AnimatedVisibility(visible = state.title.isNotEmpty()) {
        IconButton(onClick = {
          actor(AddTodoScreenEvent.TitleChanged(""))
        }) {
          Icon(Icons.Default.Clear, "Clear title")
        }
      }
    },
  )
}

@Composable
private fun DescriptionTextField(
  state: AddTodoScreenState,
  actor: (event: AddTodoScreenEvent) -> Unit
) {
  AppOutlinedTextField(
    title = "Description",
    hint = "a brief detail what this is about",
    value = state.description,
    onValueChange = {
      actor(AddTodoScreenEvent.DescriptionChanged(it))
    },
    trailingIcon = {
      AnimatedVisibility(visible = state.description.isNotEmpty()) {
        IconButton(onClick = {
          actor(AddTodoScreenEvent.DescriptionChanged(""))
        }) {
          Icon(Icons.Default.Clear, "Clear description")
        }
      }
    }
  )
}
