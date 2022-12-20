package dev.mohammadfayaz.todojc.utils.core.date

import io.kotest.matchers.shouldBe
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DayTest {
  @Test
  fun `test proper suffix for day`() {
    1.withSuffix() shouldBe "1st"
    2.withSuffix() shouldBe "2nd"
    3.withSuffix() shouldBe "3rd"

    21.withSuffix() shouldBe "21st"
    22.withSuffix() shouldBe "22nd"
    23.withSuffix() shouldBe "23rd"

    31.withSuffix() shouldBe "31st"

    for (i in 4..20) {
      i.withSuffix() shouldBe "${i}th"
    }

    for (i in 24..30) {
      i.withSuffix() shouldBe "${i}th"
    }
  }
}
