package dev.mohammadfayaz.todojc.utils.core

fun String.sentence(): String {
  return "${substring(0, 1).uppercase()}${substring(1).lowercase()}"
}
