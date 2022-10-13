package com.fayaz.todo_jc.features.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fayaz.todo_jc.design_kit.theme.color

@Composable
fun OnboardingPage(backgroundColor: Color) {
  Column(
    modifier = Modifier
      .fillMaxSize(),
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.7f)
        .padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Bottom
    ) {
      Image(
        modifier = Modifier
          .height(200.dp),
        painter = painterResource(R.drawable.onboarding_todo_list),
        contentDescription = "Onboarding Todo list"
      )
      Text(
        text = "Title",
        style = MaterialTheme.typography.h3
          .copy(fontWeight = FontWeight.Medium)
          .color(MaterialTheme.colors.onBackground),
        textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.align(Alignment.CenterHorizontally)
          .padding(vertical = 16.dp)
      )
      Text(
        text = "Some nasty description about the todo item",
        style = MaterialTheme.typography.subtitle1
          .copy(fontSize = 24.sp)
          .color(MaterialTheme.colors.onBackground),
        textAlign = TextAlign.Center,
        modifier = Modifier.align(Alignment.CenterHorizontally).height(120.dp)
      )
    }
    
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(backgroundColor),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
  }
}

@Preview
@Composable
fun OnboardingPagePreview() {
  OnboardingPage(
    backgroundColor = MaterialTheme.colors.primary
  )
}
