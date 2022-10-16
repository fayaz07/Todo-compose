package com.fayaz.todo_jc.core.logger

import timber.log.Timber

object Logger {
  fun i(t: Throwable) {
    Timber.i(t)
  }

  fun i(message: String, vararg args: Any?) {
    Timber.i(message, args)
  }

  fun e(t: Throwable) {
    Timber.e(t)
  }

  fun e(message: String, vararg args: Any?) {
    Timber.e(message, args)
  }

  fun w(t: Throwable) {
    Timber.w(t)
  }

  fun w(message: String, vararg args: Any?) {
    Timber.w(message, args)
  }

  fun d(t: Throwable) {
    Timber.d(t)
  }

  fun d(message: String, vararg args: Any?) {
    Timber.d(message, args)
  }
}
