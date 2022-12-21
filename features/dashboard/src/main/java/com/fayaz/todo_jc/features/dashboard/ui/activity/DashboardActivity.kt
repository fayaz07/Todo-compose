package com.fayaz.todo_jc.features.dashboard.ui.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardActivity : ComponentActivity(), PermissionCallback {

  @Inject
  lateinit var viewModel: DashboardViewModel

  private lateinit var permissionUtil: PermissionUtil

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      Log.d("perm", shouldShowRequestPermissionRationale(PermissionsEnum.Gallery.id).toString())
    }
//    initialize()
//    listenToEvents()

    setContent {
      AppTheme {
        val navHostController = rememberNavController()
        DashboardNavHost(navController = navHostController)
      }
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    permissionUtil.cleanUp()
  }

  private fun initialize() {
    permissionUtil = PermissionUtil(activityResultRegistry, this, this)
    lifecycle.addObserver(permissionUtil)
  }

  private fun listenToEvents() {
//    lifecycleScope.launch {
    CoroutineScope(Dispatchers.Main).launch {
      Log.d("perm", "listening to events")
      viewModel.events.receiveAsFlow().collect {
        Log.d("perm", "received event $it")
        if (it is RequestPermission) {
          handlePermissionRequest(it)
        }
      }
    }
  }

  private fun handlePermissionRequest(event: RequestPermission) {
    Log.d("perm", "requesting permission")
    permissionUtil.requestPermission(event.permission)
  }

  override fun onGranted(permission: PermissionsEnum) {
    Log.d("perm", "permission granted")
    viewModel.dispatcher(PermissionGranted(permission))
  }

  override fun onDenied(permission: PermissionsEnum) {
    Log.d("perm", "permission denied")
    viewModel.dispatcher(PermissionDenied(permission))
  }

  override fun onPermanentlyDenied(permission: PermissionsEnum) {
    Log.d("perm", "permission permanently denied")
    viewModel.dispatcher(PermissionPermanentlyDenied(permission))
  }
}
