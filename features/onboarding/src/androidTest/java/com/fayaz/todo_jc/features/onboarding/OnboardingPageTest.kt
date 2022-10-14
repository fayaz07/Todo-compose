package com.fayaz.todo_jc.features.onboarding

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.fayaz.todo_jc.design_kit.theme.AppTheme
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
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
    composeTestRule.onNodeWithContentDescription(getContext().getString(R.string.onboarding_cd_main_image))
      .assertIsDisplayed()
  }

  private fun getContext() = InstrumentationRegistry.getInstrumentation().targetContext
}