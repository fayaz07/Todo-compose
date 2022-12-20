package dev.mohammadfayaz.todojc.utils.core.date

import org.amshove.kluent.`should be equal to`
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DayTest {
  @Test
  fun `test proper suffix for day`() {
    1.withSuffix() `should be equal to`  "1st"
    2.withSuffix() `should be equal to` "2nd"
    3.withSuffix() `should be equal to` "3rd"

    21.withSuffix() `should be equal to` "21st"
    22.withSuffix() `should be equal to` "22nd"
    23.withSuffix() `should be equal to` "23rd"

    31.withSuffix() `should be equal to` "31st"

    for (i in 4..20) {
      i.withSuffix() `should be equal to` "${i}th"
    }

    for (i in 24..30) {
      i.withSuffix() `should be equal to` "${i}th"
    }
  }
}
