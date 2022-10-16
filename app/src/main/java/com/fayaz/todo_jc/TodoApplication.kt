package com.fayaz.todo_jc

import android.app.Application
import com.fayaz.todo_jc.core.logger.Logger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TodoApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    Logger.init()
  }
}
