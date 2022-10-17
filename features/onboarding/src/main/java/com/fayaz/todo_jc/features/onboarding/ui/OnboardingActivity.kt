package com.fayaz.todo_jc.features.onboarding.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fayaz.todo_jc.design_kit.theme.AppTheme
import com.fayaz.todo_jc.features.onboarding.OnboardingPage
import com.fayaz.todo_jc.features.onboarding.R
import com.fayaz.todo_jc.features.onboarding.data.onboardingData
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

class OnboardingActivity : ComponentActivity() {
  @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AppTheme {
        Scaffold {
          Content()
        }
      }
    }
  }

  @OptIn(ExperimentalPagerApi::class)
  @Composable
  fun Content() {
    val pagerState = rememberPagerState()
    Box {
      HorizontalPager(count = onboardingData.size, state = pagerState) { page ->
        OnboardingPage(onboardingData[page])
      }
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
          .padding(32.dp)
          .align(Alignment.BottomCenter)
          .fillMaxWidth()
      ) {
        HorizontalPagerIndicator(pagerState = pagerState, modifier = Modifier)
        Button(
          enabled = pagerState.currentPage == onboardingData.size - 1 && !pagerState.isScrollInProgress,
          shape = RoundedCornerShape(64.dp),
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
          onClick = { }) {
          Text(
            text = stringResource(R.string.onboarding_launch_button),
            modifier = Modifier.padding(4.dp),
            style = MaterialTheme.typography.button
              .copy(fontSize = 18.sp)
          )
        }
      }
    }
  }
}
