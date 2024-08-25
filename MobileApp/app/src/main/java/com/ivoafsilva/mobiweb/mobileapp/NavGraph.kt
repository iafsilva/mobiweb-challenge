package com.ivoafsilva.mobiweb.mobileapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ivoafsilva.mobiweb.mobileapp.ui.home.HomeScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun SetupNavGraph(startDestination: String) = NavHost(
    navController = rememberNavController(),
    startDestination = startDestination
) {
    composable(NavScreen.HomeScreen.route) {
        HomeScreen(
            viewModel = koinViewModel()
        )
    }
}

/**
 * Navigation Screens: defines the navigation route and any needed arguments.
 * - HomeScreen: Shows a text input for the user to send messages to a remote server
 */
sealed class NavScreen(val route: String) {

    data object HomeScreen : NavScreen("home_screen") {
        const val testTag = "test_main_screen"
    }
}