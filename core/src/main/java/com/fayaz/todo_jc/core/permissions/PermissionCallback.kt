package com.fayaz.todo_jc.core.permissions

interface PermissionCallback {
  fun onGranted(permission: PermissionsEnum)

  fun onDenied(permission: PermissionsEnum)

  fun onPermanentlyDenied(permission: PermissionsEnum)
}
