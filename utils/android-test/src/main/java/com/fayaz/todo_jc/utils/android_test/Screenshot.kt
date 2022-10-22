package com.fayaz.todo_jc.utils.android_test

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onRoot
import java.io.FileOutputStream

const val SCREENSHOT_QUALITY = 100
const val SCREENSHOT_SUPPORTED_API_VERSION = Build.VERSION_CODES.O

fun SemanticsNodeInteractionsProvider.snapshot(fileName: String, context: Context) {
  if (Build.VERSION.SDK_INT > SCREENSHOT_SUPPORTED_API_VERSION) {
    onRoot()
      .captureToImage()
      .asAndroidBitmap()
      .save(fileName, context)
  }
}

fun Bitmap.save(fileName: String, context: Context) {
  FileOutputStream(getScreenshotsPath(fileName, context)).use { out ->
    compress(Bitmap.CompressFormat.PNG, SCREENSHOT_QUALITY, out)
  }
}

private fun getScreenshotsPath(fileName: String, context: Context): String {
  val path = context.filesDir.canonicalPath
  return "$path/$fileName"
}
