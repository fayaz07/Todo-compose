package com.fayaz.todo_jc.features.dashboard.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.fayaz.todo_jc.features.dashboard.ui.screens.home.HomeScreen

@Composable
fun DashboardNavHost(navController: NavHostController) {
  NavHost(navController = navController, startDestination = DashboardRoutes.home) {
    composable(DashboardRoutes.home) {
      HomeScreen()
    }
  }
}
