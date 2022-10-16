package com.fayaz.todo_jc.utils.android_test

import android.graphics.Bitmap
import android.os.Build
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onRoot
import java.io.FileOutputStream

const val SCREENSHOT_QUALITY = 100
const val SCREENSHOT_SUPPORTED_API_VERSION = Build.VERSION_CODES.O

fun SemanticsNodeInteractionsProvider.snapshot(fileName: String) {
  if (Build.VERSION.SDK_INT >= SCREENSHOT_SUPPORTED_API_VERSION) {
    onRoot()
      .captureToImage()
      .asAndroidBitmap()
      .save(fileName)
  }
}

fun Bitmap.save(fileName: String) {
  FileOutputStream(getScreenshotsPath(fileName)).use { out ->
    compress(Bitmap.CompressFormat.PNG, SCREENSHOT_QUALITY, out)
  }
}

private fun getScreenshotsPath(fileName: String): String {
  val path = useContext().filesDir.canonicalPath
  return "$path/$fileName"
}