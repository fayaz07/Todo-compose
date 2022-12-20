package dev.mohammadfayaz.todojc.utils.core.date

import io.kotest.matchers.shouldBe
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TimeTest {
  @Test
  fun `test time generalization`() {
    generalizeTime(0,0) shouldBe "00:00 AM"
    generalizeTime(1,20) shouldBe "01:20 AM"
    generalizeTime(2,4) shouldBe "02:04 AM"
    generalizeTime(10,0) shouldBe "10:00 AM"
    generalizeTime(9,7) shouldBe "09:07 AM"
    generalizeTime(9,17) shouldBe "09:17 AM"
    generalizeTime(12,0) shouldBe "12:00 AM"
    generalizeTime(14,0) shouldBe "02:00 PM"
    generalizeTime(24,0) shouldBe "12:00 PM"
    generalizeTime(16,16) shouldBe "04:16 PM"
  }
}
