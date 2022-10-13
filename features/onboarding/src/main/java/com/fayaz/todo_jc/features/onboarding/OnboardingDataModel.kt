package com.fayaz.todo_jc.features.onboarding

import androidx.annotation.DrawableRes

data class OnboardingDataModel(
  val title: String,
  @DrawableRes val image: Int,
  val description: String
)
