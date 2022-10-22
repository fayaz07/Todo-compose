package com.fayaz.todo_jc.features.onboarding

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fayaz.todo_jc.design_kit.theme.AppTheme
import com.fayaz.todo_jc.features.onboarding.data.OnboardingDataModel
import com.fayaz.todo_jc.features.onboarding.ui.OnboardingPage
import com.fayaz.todo_jc.utils.android_test.useContext
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

@HiltAndroidTest
class OnboardingPageTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun displays_supplied_data() {
    val pageData = OnboardingDataModel(
      title = "Test title",
      description = "Some random description about the infographics",
      image = R.drawable.onboarding_todo_list
    )
    composeTestRule.setContent {
      AppTheme {
        OnboardingPage(item = pageData)
      }
    }
    composeTestRule.onNodeWithText(pageData.title).assertIsDisplayed()
    composeTestRule.onNodeWithText(pageData.description).assertIsDisplayed()
    composeTestRule.onNodeWithContentDescription(useContext().getString(R.string.onboarding_cd_main_image))
      .assertIsDisplayed()
  }

  @Test
  fun items_are_not_clickable() {
    val pageData = OnboardingDataModel(
      title = "Test title",
      description = "Some random description about the infographics",
      image = R.drawable.onboarding_todo_list
    )
    composeTestRule.setContent {
      AppTheme {
        OnboardingPage(item = pageData)
      }
    }
    composeTestRule.onRoot().performTouchInput {
      swipeLeft()
    }
    composeTestRule.onNodeWithText(pageData.title).performClick().assertIsDisplayed()
    composeTestRule.onNodeWithText(pageData.description).performClick().assertIsDisplayed()
    composeTestRule.onNodeWithContentDescription(useContext().getString(R.string.onboarding_cd_main_image))
      .performClick()
      .assertIsDisplayed()
  }
}
