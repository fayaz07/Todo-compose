package com.fayaz.todo_jc.core.permissions

fun String.getPermissionEnum(): PermissionsEnum? {
  for (p in PermissionsEnum.values()) {
    if (p.id == this) {
      return p
    }
  }
  return null
}
