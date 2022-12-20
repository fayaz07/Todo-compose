package com.fayaz.todo_jc.features.dashboard.ui.activity

import com.fayaz.todo_jc.core.base.vm.ViewEvent
import com.fayaz.todo_jc.core.permissions.PermissionsEnum

sealed class DashboardEvent : ViewEvent() {
  data class RequestPermission(val permission: PermissionsEnum) : DashboardEvent()
  data class PermissionGranted(val permission: PermissionsEnum) : DashboardEvent()
  data class PermissionDenied(val permission: PermissionsEnum) : DashboardEvent()
  data class PermissionPermanentlyDenied(val permission: PermissionsEnum) : DashboardEvent()
}
