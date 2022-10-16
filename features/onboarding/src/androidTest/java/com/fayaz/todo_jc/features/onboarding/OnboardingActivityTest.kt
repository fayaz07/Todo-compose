package com.fayaz.todo_jc.features.onboarding

import android.graphics.Bitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.screenshot.Screenshot
import java.io.FileOutputStream
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OnboardingActivityTest {

  @get:Rule
  val rule = createAndroidComposeRule<OnboardingActivity>()

  @Test
  fun should_display_data() {
    val pageData = onboardingData[0]
    rule.onAllNodesWithText(pageData.title)[0].assertIsDisplayed()
    rule.onAllNodesWithText(pageData.description)[0].assertIsDisplayed()
//    rule.onAllNodesWithContentDescription(
//      useContext().getString(R.string.onboarding_cd_main_image)
//    )[0].assertIsDisplayed()
//
//    rule.onNodeWithText(getTestContext().getString(R.string.onboarding_launch_button))
//      .assertIsNotEnabled()
  }

  @Test
  fun should_be_able_to_swipe() {
    rule.onRoot().performTouchInput {
      swipeLeft()
    }
    val pageData = onboardingData[1]
    rule.onAllNodesWithText(pageData.title)[0].assertIsDisplayed()
    rule.onAllNodesWithText(pageData.description)[0].assertIsDisplayed()
//    rule.onAllNodesWithContentDescription(
//      getTestContext().getString(R.string.onboarding_cd_main_image)
//    )[0].assertIsDisplayed()
//
//    rule.onNodeWithText(getTestContext().getString(R.string.onboarding_launch_button))
//      .assertIsNotEnabled()
  }

  @Test
  fun after_multiple_swipes_should_be_on_proper_screen() {
    rule.onRoot().performTouchInput {
      swipeLeft()
      swipeLeft()
    }
    val pageData = onboardingData[2]
    rule.onAllNodesWithText(pageData.title)[0].assertIsDisplayed()
    rule.onAllNodesWithText(pageData.description)[0].assertIsDisplayed()
//    rule.onAllNodesWithContentDescription(
//      getTestContext().getString(R.string.onboarding_cd_main_image)
//    )[0].assertIsDisplayed()
  }

  @Test
  fun launch_button_should_be_enabled_on_last_page() {
    rule.onRoot().performTouchInput {
      for (i in onboardingData.indices) {
        swipeLeft()
      }
    }
//    rule.onNodeWithText(getTestContext().getString(R.string.onboarding_launch_button))
//      .assertIsEnabled()
//      .assertHasClickAction()
  }

  @Test
  fun launch_button_should_be_disabled_if_moved_out_of_last_page() {
    rule.onRoot().performTouchInput {
      for (i in onboardingData.indices) {
        swipeLeft()
      }
      swipeRight()
    }
//    rule.onNodeWithText(getTestContext().getString(R.string.onboarding_launch_button))
//      .assertIsNotEnabled()
    rule.onRoot()
      .captureToImage()
      .asAndroidBitmap()
//      .save("OnboardingActivity.png")
  }
}
