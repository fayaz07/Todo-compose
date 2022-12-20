package com.fayaz.todo_jc.features.dashboard.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.fayaz.todo_jc.core.permissions.PermissionCallback
import com.fayaz.todo_jc.core.permissions.PermissionUtil
import com.fayaz.todo_jc.core.permissions.PermissionsEnum
import com.fayaz.todo_jc.design_kit.theme.AppTheme
import com.fayaz.todo_jc.features.dashboard.ui.activity.DashboardEvent.PermissionDenied
import com.fayaz.todo_jc.features.dashboard.ui.activity.DashboardEvent.PermissionGranted
import com.fayaz.todo_jc.features.dashboard.ui.activity.DashboardEvent.PermissionPermanentlyDenied
import com.fayaz.todo_jc.features.dashboard.ui.activity.DashboardEvent.RequestPermission
import com.fayaz.todo_jc.features.dashboard.ui.navigation.DashboardNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardActivity : ComponentActivity(), PermissionCallback {

  private val viewModel: DashboardViewModel by viewModels()
  private lateinit var permissionUtil: PermissionUtil

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AppTheme {
        val navHostController = rememberNavController()
        DashboardNavHost(navController = navHostController)
      }
    }

    initialize()
    listenToEvents()
  }

  override fun onDestroy() {
    super.onDestroy()
    permissionUtil.cleanUp()
  }

  private fun initialize() {
    permissionUtil = PermissionUtil(activityResultRegistry, this, this)
  }

  private fun listenToEvents() {
    lifecycleScope.launch {
      viewModel.events.receiveAsFlow().collect {
        if (it is RequestPermission) {
          handlePermissionRequest(it)
        }
      }
    }
  }

  private fun handlePermissionRequest(event: RequestPermission) {
    permissionUtil.requestPermission(event.permission)
  }

  override fun onGranted(permission: PermissionsEnum) {
    viewModel.dispatcher(PermissionGranted(permission))
  }

  override fun onDenied(permission: PermissionsEnum) {
    viewModel.dispatcher(PermissionDenied(permission))
  }

  override fun onPermanentlyDenied(permission: PermissionsEnum) {
    viewModel.dispatcher(PermissionPermanentlyDenied(permission))
  }
}
