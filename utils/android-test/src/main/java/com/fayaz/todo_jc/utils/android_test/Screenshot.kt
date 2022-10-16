package com.fayaz.todo_jc.utils.android_test

import android.graphics.Bitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onRoot
import java.io.FileOutputStream

fun SemanticsNodeInteractionsProvider.snapshot(fileName: String) {
  onRoot()
    .captureToImage()
    .asAndroidBitmap()
    .save(fileName)
}

fun Bitmap.save(fileName: String) {
  FileOutputStream(getScreenshotsPath(fileName)).use { out ->
    compress(Bitmap.CompressFormat.PNG, 100, out)
  }
}

private fun getScreenshotsPath(fileName: String): String {
  val path = useContext().filesDir.canonicalPath
  return "$path/$fileName"
}
