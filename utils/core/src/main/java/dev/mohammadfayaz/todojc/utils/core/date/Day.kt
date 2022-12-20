@file:Suppress("MagicNumber")
package dev.mohammadfayaz.todojc.utils.core.date

fun Int.withSuffix(): String {
  return if (this == 1 || this % 10 == 1 && this != 11) {
    "${this}st"
  } else if (this == 2 || this % 10 == 2 && this != 12) {
    "${this}nd"
  } else if (this == 3 || this % 10 == 3 && this != 13) {
    "${this}rd"
  } else {
    "${this}th"
  }
}
