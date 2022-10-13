package com.fayaz.todo_jc.features.onboarding

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fayaz.todo_jc.design_kit.theme.color

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
        modifier = Modifier
          .height(200.dp),
        painter = painterResource(item.image),
        contentDescription = item.title
      )
      Text(
        text = item.title,
        style = MaterialTheme.typography.h4
          .copy(fontWeight = FontWeight.Medium)
          .color(MaterialTheme.colors.onBackground),
        textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier
          .align(Alignment.CenterHorizontally)
          .padding(vertical = 16.dp)
      )
      Text(
        text = item.description,
        style = MaterialTheme.typography.subtitle1
          .copy(fontSize = 20.sp)
          .color(MaterialTheme.colors.onBackground),
        textAlign = TextAlign.Center,
        modifier = Modifier
          .align(Alignment.CenterHorizontally)
          .height(120.dp)
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