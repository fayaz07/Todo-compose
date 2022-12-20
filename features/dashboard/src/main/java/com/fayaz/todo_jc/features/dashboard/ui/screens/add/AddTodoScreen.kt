package com.fayaz.todo_jc.features.dashboard.ui.screens.add

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fayaz.todo_jc.design_kit.composables.AppOutlinedTextField
import com.fayaz.todo_jc.design_kit.composables.Space
import com.fayaz.todo_jc.design_kit.composables.TimePicker
import com.fayaz.todo_jc.design_kit.theme.Spacing16
import com.fayaz.todo_jc.design_kit.theme.Spacing24
import com.fayaz.todo_jc.design_kit.theme.Spacing4
import com.fayaz.todo_jc.design_kit.theme.Spacing8
import com.fayaz.todo_jc.design_kit.theme.TodoAppTypography

@Composable
@Preview
private fun Preview() {
  Content(
    AddTodoScreenState(
      loading = false, title = "", description = "",
      recurring = false,
      frequencyDropDownExpanded = false,
      selectedFrequency = EventFrequencyEnum.Daily,
      hour = 0, minute = 0
    )
  ) {}
}

@Composable
fun AddTodoScreen() {
  val viewModel: AddTodoScreenViewModel = hiltViewModel()
  val state = viewModel.viewState.collectAsState().value
  val actor = viewModel::dispatcher

  Content(state, actor)
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Content(
  state: AddTodoScreenState,
  actor: (event: AddTodoScreenEvent) -> Unit,
) {
  val keyboardController = LocalSoftwareKeyboardController.current
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
    Body(it, state, actor, keyboardController)
  }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Body(
  paddingValues: PaddingValues,
  state: AddTodoScreenState,
  actor: (event: AddTodoScreenEvent) -> Unit,
  keyboardController: SoftwareKeyboardController?
) {
  val focusManager = LocalFocusManager.current
  Column(
    modifier = Modifier
      .padding(paddingValues)
      .padding(top = Spacing24)
  ) {
    TitleTextField(state, actor)
    Space(Spacing8)
    DescriptionTextField(state, actor)
    Space(Spacing4)
    RecursiveToggle(state, actor, keyboardController, focusManager)
    AnimatedVisibility(visible = state.recurring) {
      FrequencyDropDown(
        state,
        actor,
        keyboardController,
        focusManager
      )
    }
    AnimatedVisibility(visible = state.recurring) {
      EventFrequencyField(state.selectedFrequency)
    }
    AtTimeField(state, actor)
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun RecursiveToggle(
  state: AddTodoScreenState,
  actor: (event: AddTodoScreenEvent) -> Unit,
  keyboardController: SoftwareKeyboardController?,
  focusManager: FocusManager,
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = Spacing16),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      text = "Recurring",
      style = TodoAppTypography.body1.copy(fontWeight = FontWeight.Medium)
    )
    Switch(
      checked = state.recurring,
      onCheckedChange = {
        focusManager.clearFocus()
        keyboardController?.hide()
        actor(AddTodoScreenEvent.RecurringChanged(it))
      }
    )
  }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun FrequencyDropDown(
  state: AddTodoScreenState,
  actor: (event: AddTodoScreenEvent) -> Unit,
  keyboardController: SoftwareKeyboardController?,
  focusManager: FocusManager
) {
  val items = EventFrequencyEnum.values()
  val dropDownMenuWidth = (LocalConfiguration.current.screenWidthDp - 32).dp

  Column {
    AppOutlinedTextField(
      modifier = Modifier
        .onFocusChanged {
          if (it.hasFocus || it.isFocused) {
            keyboardController?.hide()
            actor(AddTodoScreenEvent.FrequencyDropDownExpanded(true))
          }
        },
      title = "Frequency",
      hint = "",
      value = state.selectedFrequency.display,
      onValueChange = {},
      readOnly = true,
    )
    Box(
      modifier = Modifier.padding(horizontal = Spacing16)
    ) {
      DropdownMenu(
        modifier = Modifier.width(dropDownMenuWidth),
        expanded = state.frequencyDropDownExpanded,
        onDismissRequest = {
          keyboardController?.hide()
          focusManager.clearFocus()
          actor(AddTodoScreenEvent.FrequencyDropDownExpanded(false))
        },
      ) {
        items.forEachIndexed { _, s ->
          DropdownMenuItem(
            onClick = {
              actor(AddTodoScreenEvent.FrequencyChanged(s))
              actor(AddTodoScreenEvent.FrequencyDropDownExpanded(false))
              focusManager.clearFocus()
            }
          ) {
            Text(text = s.display)
          }
        }
      }
    }
  }
}

@Composable
private fun EventFrequencyField(selectedFrequency: EventFrequencyEnum) {
  when (selectedFrequency) {
    EventFrequencyEnum.Daily -> {}
    EventFrequencyEnum.Weekly -> {}
    EventFrequencyEnum.SpecificDays -> {}
    EventFrequencyEnum.Monthly -> {}
    EventFrequencyEnum.Yearly -> {}
  }
}

@Composable
private fun AtTimeField(state: AddTodoScreenState, actor: (event: AddTodoScreenEvent) -> Unit) {
  TimePicker(label = "At", value = generalizedTime(state.hour, state.minute)) { h, m ->
    actor(AddTodoScreenEvent.TimePicked(h, m))
  }
}

// This code definitely need refactoring
private fun generalizedTime(hour: Int, minute: Int): String {
  var meridian = "AM"
  val min = if (minute > 9) {
    minute
  } else {
    "0$minute"
  }
  val hrInt = if (hour > 12) {
    meridian = "PM"
    hour - 12
  } else {
    hour
  }
  val hr = if (hrInt < 10) {
    "0$hrInt"
  } else {
    hrInt
  }
  return "${hr}:${min} $meridian"
}
