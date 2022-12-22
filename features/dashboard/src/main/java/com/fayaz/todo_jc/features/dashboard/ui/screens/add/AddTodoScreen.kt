@file:Suppress("TooManyFunctions")

package com.fayaz.todo_jc.features.dashboard.ui.screens.add

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fayaz.todo_jc.core.content.ImagePicker
import com.fayaz.todo_jc.core.permissions.DialogAction
import com.fayaz.todo_jc.core.permissions.PermissionUtilCompose
import com.fayaz.todo_jc.core.permissions.PermissionsEnum
import com.fayaz.todo_jc.design_kit.composables.AddImageComposable
import com.fayaz.todo_jc.design_kit.composables.AppDropDownList
import com.fayaz.todo_jc.design_kit.composables.AppOutlinedTextField
import com.fayaz.todo_jc.design_kit.composables.AttachmentComposable
import com.fayaz.todo_jc.design_kit.composables.FieldTitle
import com.fayaz.todo_jc.design_kit.composables.PermissionDialog
import com.fayaz.todo_jc.design_kit.composables.Space
import com.fayaz.todo_jc.design_kit.composables.TimePicker
import com.fayaz.todo_jc.design_kit.composables.verticalScrollbar
import com.fayaz.todo_jc.design_kit.theme.DeepPurple600
import com.fayaz.todo_jc.design_kit.theme.Spacing12
import com.fayaz.todo_jc.design_kit.theme.Spacing16
import com.fayaz.todo_jc.design_kit.theme.Spacing24
import com.fayaz.todo_jc.design_kit.theme.Spacing4
import com.fayaz.todo_jc.design_kit.theme.Spacing8
import com.fayaz.todo_jc.design_kit.theme.TodoAppTypography
import com.fayaz.todo_jc.features.dashboard.ui.activity.DashboardActivity
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import dev.mohammadfayaz.todojc.utils.core.constants.DateTimeConstants.MIN_DAYS_OF_MONTH
import dev.mohammadfayaz.todojc.utils.core.date.Month
import dev.mohammadfayaz.todojc.utils.core.date.generalizeTime
import dev.mohammadfayaz.todojc.utils.core.extensions.sentence
import java.time.DayOfWeek

@Composable
@Preview
private fun Preview() {
  Content(
    AddTodoScreenState(
      loading = false, title = "", description = "",
      recurring = false,
      selectedFrequency = EventFrequencyEnum.Daily,
      hour = 0, minute = 0, selectedDaysOfWeek = emptyList(),
      selectedDayOfMonth = 1, selectedMonth = Month.JANUARY,
      showPermissionDialog = false, reqCurrentPermission = PermissionsEnum.Gallery,
      attachments = emptyList()
    )
  ) {}
}

@Composable
fun AddTodoScreen() {
  val viewModel: AddTodoScreenViewModel = hiltViewModel()
  val state = viewModel.viewState.collectAsState().value
  val actor = viewModel::dispatcher
  val context = LocalContext.current

  ListenToEvents(viewModel)

  Surface {
    if (state.showPermissionDialog && state.reqCurrentPermission != null) {
      PermissionDialog(permission = state.reqCurrentPermission, {
        actor(AddTodoScreenEvent.PermissionDialogAction(DialogAction.NEGATIVE))
      }) {
        actor(AddTodoScreenEvent.PermissionDialogAction(DialogAction.CONFIRM))
        openSettingsScreen(context = context)
      }
    }
    Content(state, actor)
  }
}

private fun openSettingsScreen(context: Context) {
  val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
  intent.data = Uri.fromParts("package", context.packageName, null)
  context.startActivity(intent)
}

@Composable
fun ListenToEvents(viewModel: AddTodoScreenViewModel) {
  val activity = LocalContext.current as DashboardActivity
  val permissionUtil = PermissionUtilCompose(activity, viewModel)
  permissionUtil.Initialize()

  val imagePicker = ImagePicker {
    viewModel.dispatcher(AddTodoScreenEvent.AttachmentEvent.ImagePicked(it))
  }
  imagePicker.Initialize()

  LaunchedEffect(Unit) {
    viewModel.viewEvents.collect {
      when (it) {
        is AddTodoScreenEvent.AttachmentEvent.LaunchPicker -> {
          imagePicker.launch()
        }
        is AddTodoScreenEvent.RequestPermission -> {
          permissionUtil.requestPermission(it.permission)
        }
        is AddTodoScreenEvent.SnackBar -> {
          // TODO: Implement snackbar
        }
        else -> {}
      }
    }
  }

  DisposableEffect(Unit) {
    onDispose {
      permissionUtil.cleanUp()
      imagePicker.cleanUp()
    }
  }
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
      .verticalScroll(rememberScrollState())
      .verticalScrollbar(rememberLazyListState())
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
      EventFrequencyTitle(state.selectedFrequency)
    }
    AnimatedVisibility(visible = state.recurring) {
      EventFrequencyField(state, actor, keyboardController, focusManager)
    }
    TimePicker(label = "At", value = generalizeTime(state.hour, state.minute)) { h, m ->
      actor(AddTodoScreenEvent.FormEvent.TimePicked(h, m))
    }
    AttachmentsComposable(state, actor)
  }
}

@Composable
private fun AttachmentsComposable(
  state: AddTodoScreenState,
  actor: (event: AddTodoScreenEvent) -> Unit
) {
  FlowRow(
    modifier = Modifier.padding(horizontal = Spacing16, vertical = Spacing8),
    crossAxisAlignment = FlowCrossAxisAlignment.Center
  ) {
    FieldTitle(
      title = "Add Attachments (Optional)",
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = Spacing8)
    )
    for (i in 0 until state.attachments.size) {
      AttachmentComposable(
        modifier = Modifier.padding(4.dp),
        uri = state.attachments[i]
      ) {
        actor(AddTodoScreenEvent.AttachmentEvent.Remove(i))
      }
    }
    AddImageComposable(
      modifier = Modifier.padding(vertical = Spacing8),
      size = 64.dp,
    ) {
      actor(AddTodoScreenEvent.AttachmentEvent.LaunchPicker)
    }
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
      actor(AddTodoScreenEvent.FormEvent.TitleChanged(it))
    },
    trailingIcon = {
      AnimatedVisibility(visible = state.title.isNotEmpty()) {
        IconButton(onClick = {
          actor(AddTodoScreenEvent.FormEvent.TitleChanged(""))
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
      actor(AddTodoScreenEvent.FormEvent.DescriptionChanged(it))
    },
    trailingIcon = {
      AnimatedVisibility(visible = state.description.isNotEmpty()) {
        IconButton(onClick = {
          actor(AddTodoScreenEvent.FormEvent.DescriptionChanged(""))
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
    FieldTitle(title = "Recurring")
    Switch(
      checked = state.recurring,
      onCheckedChange = {
        focusManager.clearFocus()
        keyboardController?.hide()
        actor(AddTodoScreenEvent.FormEvent.RecurringChanged(it))
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
  val items = EventFrequencyEnum.values().toList()

  AppDropDownList(
    label = "Frequency",
    value = state.selectedFrequency.display,
    items = items,
    height = 250.dp,
    display = { it.display },
    onSelected = {
      actor(AddTodoScreenEvent.FormEvent.FrequencyChanged(it))
      focusManager.clearFocus()
    },
    onExpanded = { keyboardController?.hide() }
  ) {
    keyboardController?.hide()
    focusManager.clearFocus()
  }
}

@Composable
private fun EventFrequencyTitle(
  selectedFrequency: EventFrequencyEnum
) {
  val title = when (selectedFrequency) {
    EventFrequencyEnum.Weekly -> "Pick day of the week"
    EventFrequencyEnum.SpecificDays -> "Pick days of the week"
    else -> null
  }
  title?.let {
    FieldTitle(
      modifier = Modifier.padding(start = Spacing16, top = Spacing8),
      title = title
    )
  }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun EventFrequencyField(
  state: AddTodoScreenState,
  actor: (event: AddTodoScreenEvent) -> Unit,
  keyboardController: SoftwareKeyboardController?,
  focusManager: FocusManager
) {
  return when (state.selectedFrequency) {
    EventFrequencyEnum.Daily -> {}
    EventFrequencyEnum.Weekly -> {
      // On any one day
      PickDaysOfWeek(state.selectedDaysOfWeek, actor)
    }
    EventFrequencyEnum.SpecificDays -> {
      // on few days of week
      PickDaysOfWeek(state.selectedDaysOfWeek, actor)
    }
    EventFrequencyEnum.Monthly -> {
      // pick one day from (1 to 29)
      DaysDropDown(
        state,
        actor,
        keyboardController,
        focusManager
      )
    }
    EventFrequencyEnum.Yearly -> {
      // pick month and day
      Column {
        MonthsDropDown(state, actor, keyboardController, focusManager)
        DaysDropDown(
          state,
          actor,
          keyboardController,
          focusManager
        )
      }
    }
  }
}

@Composable
private fun PickDaysOfWeek(
  selectedDaysOfWeek: List<DayOfWeek>,
  actor: (event: AddTodoScreenEvent) -> Unit
) {
  val days = DayOfWeek.values()
  FlowRow(
    modifier = Modifier
      .padding(horizontal = Spacing12)
      .padding(bottom = Spacing8)
  ) {
    for (day in days) {
      val isSelected = selectedDaysOfWeek.any { selected -> selected == day }
      if (isSelected) {
        SelectedDayChip(day, actor)
      } else {
        UnSelectedDayChip(day, actor)
      }
    }
  }
}

@Composable
private fun SelectedDayChip(day: DayOfWeek, actor: (event: AddTodoScreenEvent) -> Unit) {
  val interactionSource = remember { MutableInteractionSource() }
  Card(
    modifier = Modifier
      .padding(Spacing4)
      .clickable(
        interactionSource = interactionSource,
        indication = null
      ) {
        actor(AddTodoScreenEvent.FormEvent.UnSelectedDayOfWeek(day))
      },
    shape = RoundedCornerShape(Spacing16),
    backgroundColor = DeepPurple600
  ) {
    DayChipText(day.name, true)
  }
}

@Composable
private fun UnSelectedDayChip(day: DayOfWeek, actor: (event: AddTodoScreenEvent) -> Unit) {
  val interactionSource = remember { MutableInteractionSource() }
  Card(
    modifier = Modifier
      .padding(Spacing4)
      .clickable(
        interactionSource = interactionSource,
        indication = null
      ) {
        actor(AddTodoScreenEvent.FormEvent.SelectedDayOfWeek(day))
      },
    border = BorderStroke(1.dp, DeepPurple600),
    shape = RoundedCornerShape(Spacing16),
  ) {
    DayChipText(day.name, false)
  }
}

@Composable
fun DayChipText(name: String, isSelected: Boolean) {
  Text(
    text = name,
    textAlign = TextAlign.Center,
    modifier = Modifier.padding(horizontal = Spacing16, vertical = Spacing4),
    style = TodoAppTypography.body2.copy(
      fontWeight = FontWeight.Medium
    ),
    color = if (isSelected) {
      Color.White
    } else {
      Color.Black
    }
  )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun DaysDropDown(
  state: AddTodoScreenState,
  actor: (event: AddTodoScreenEvent) -> Unit,
  keyboardController: SoftwareKeyboardController?,
  focusManager: FocusManager
) {
  val days = remember {
    generateSequence(1) { it + 1 }.take(MIN_DAYS_OF_MONTH).toList()
  }
  AppDropDownList(
    label = "On",
    value = state.selectedDayOfMonth.toString(),
    items = days,
    display = { e -> e.toString() },
    onSelected = {
      actor(AddTodoScreenEvent.FormEvent.SelectedDayOfMonth(it))
      focusManager.clearFocus()
    },
    onExpanded = {
      keyboardController?.hide()
    }
  ) {
    keyboardController?.hide()
    focusManager.clearFocus()
  }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun MonthsDropDown(
  state: AddTodoScreenState,
  actor: (event: AddTodoScreenEvent) -> Unit,
  keyboardController: SoftwareKeyboardController?,
  focusManager: FocusManager
) {
  val days = Month.values().toList()

  AppDropDownList(
    label = "Of",
    value = state.selectedMonth.toString().sentence(),
    items = days,
    display = { e -> e.name.sentence() },
    onSelected = {
      actor(AddTodoScreenEvent.FormEvent.SelectedMonth(it))
      focusManager.clearFocus()
    },
    onExpanded = {
      keyboardController?.hide()
    }
  ) {
    keyboardController?.hide()
    focusManager.clearFocus()
  }
}
