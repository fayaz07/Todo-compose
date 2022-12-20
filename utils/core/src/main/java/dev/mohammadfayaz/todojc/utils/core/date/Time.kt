package dev.mohammadfayaz.todojc.utils.core.date

import dev.mohammadfayaz.todojc.utils.core.constants.DateTimeConstants

// This code definitely need refactoring
fun generalizeTime(hour: Int, minute: Int): String {
  var meridian = "AM"
  val min = if (minute < DateTimeConstants.DOUBLE_DIGIT) {
    "0$minute"
  } else {
    minute
  }
  val hrInt = if (hour > DateTimeConstants.MAX_HOURS) {
    meridian = "PM"
    hour - DateTimeConstants.MAX_HOURS
  } else {
    hour
  }
  val hr = if (hrInt < DateTimeConstants.DOUBLE_DIGIT) {
    "0$hrInt"
  } else {
    hrInt
  }
  return "${hr}:${min} $meridian"
}
