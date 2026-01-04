package com.example.myfirstcal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.myfirstcal.ui.screens.HomeScreen
import com.example.myfirstcal.ui.screens.DetailScreen

/**
 * Sealed class representing app navigation routes.
 */
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Settings : Screen("settings")
}

/**
 * Top level NavHost for the app.
 */
@Composable
fun NavGraph(startDestination: String = Screen.Home.route) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToSettings = { navController.navigate(Screen.Settings.route) }
            )
        }
        composable(Screen.Settings.route) {
            DetailScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}