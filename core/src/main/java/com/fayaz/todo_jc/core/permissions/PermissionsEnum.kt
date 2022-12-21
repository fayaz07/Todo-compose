package com.fayaz.todo_jc.core.permissions

import android.Manifest

enum class PermissionsEnum(val id: String) {
  Gallery(Manifest.permission.READ_EXTERNAL_STORAGE)
}
