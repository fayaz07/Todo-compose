package com.fayaz.todo_jc.features.onboarding

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry

fun getTestContext(): Context {
  return InstrumentationRegistry.getInstrumentation().targetContext
}
