package com.fayaz.todo_jc.core.permissions

fun String.getPermissionEnum(): PermissionsEnum? {
  for (p in PermissionsEnum.values()) {
    if (p.permission == this) {
      return p
    }
  }
  return null
}
