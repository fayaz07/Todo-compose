package com.fayaz.todo_jc.features.onboarding.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fayaz.todo_jc.design_kit.theme.color
import com.fayaz.todo_jc.features.onboarding.R
import com.fayaz.todo_jc.features.onboarding.data.OnboardingDataModel
import com.fayaz.todo_jc.features.onboarding.data.onboardingData

@Composable
fun OnboardingPage(item: OnboardingDataModel) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight()
      .padding(32.dp)
      .padding(bottom = 120.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Bottom
  ) {
    Image(
      modifier = Modifier.height(200.dp),
      painter = painterResource(item.image),
      contentDescription = stringResource(R.string.onboarding_cd_main_image)
    )
    Text(
      text = item.title,
      style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold)
        .color(MaterialTheme.colors.onBackground),
      textAlign = TextAlign.Center,
      maxLines = 1,
      modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 16.dp)
    )
    Text(
      text = item.description,
      style = MaterialTheme.typography.subtitle1.copy(fontSize = 20.sp)
        .color(MaterialTheme.colors.onBackground),
      textAlign = TextAlign.Center,
      modifier = Modifier.align(Alignment.CenterHorizontally).height(120.dp)
    )
  }
}

@Preview
@Composable
fun OnboardingPagePreview() {
  OnboardingPage(
    onboardingData[0]
  )
}
