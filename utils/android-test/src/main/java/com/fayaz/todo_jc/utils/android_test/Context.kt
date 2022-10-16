package com.fayaz.todo_jc.utils.android_test

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry

fun useContext(): Context {
  return InstrumentationRegistry.getInstrumentation().targetContext
}
