package com.fayaz.todo_jc.core.permissions

import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class PermissionUtilCompose(
  private val activity: Activity,
  private val callback: PermissionCallback
) : DefaultLifecycleObserver {

//  private val permissionsKey = "permissions"
  private lateinit var requester: ActivityResultLauncher<Array<String>>

  @Composable
  fun SetupForComposable() {
    requester = rememberLauncherForActivityResult(
      ActivityResultContracts.RequestMultiplePermissions()
    ) {
      handlePermissionRequest(it)
    }
  }

  private fun handlePermissionRequest(result: Map<String, Boolean>) {
    for (entry in result) {
      val currentPermission = entry.key.getPermissionEnum()!!
      if (entry.value) {
        callback.onGranted(currentPermission)
        return
      }

      if (shouldShowRequestPermissionRationale(activity, currentPermission.id)) {
        callback.onPermanentlyDenied(currentPermission)
      } else {
        callback.onDenied(currentPermission)
      }
    }
  }

  private fun hasPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(
      activity.applicationContext,
      permission
    ) == PackageManager.PERMISSION_GRANTED
  }

  fun requestPermission(permission: PermissionsEnum) {
//    if (hasPermission(permission.id)) {
//      callback.onGranted(permission)
//      return
//    }
//    if (shouldShowRequestPermissionRationale(activity, permission.id)) {
//      callback.onPermanentlyDenied(permission)
//      return
//    }
    requester.launch(listOf(permission.id).toTypedArray())
  }

  fun cleanUp() {
    requester.unregister()
  }

  override fun onDestroy(owner: LifecycleOwner) {
    super.onDestroy(owner)
    requester.unregister()
  }
}
