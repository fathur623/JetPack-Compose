package com.example.submissioncompose.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object DetailGitar : Screen("home/{gitarId}") {
        fun createRoute(gitarId: Int) = "home/$gitarId"
    }
}