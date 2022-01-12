package com.guness.lottie.core.navigation

/**
 * Created by guness on 21.11.2021 15:29
 */
sealed class Screen(val route: String) {
    object Home : Screen(route = "home")
    object Category : Screen(route = "category")
    object Favorite : Screen(route = "favorite")
    object Profile : Screen(route = "profile")
}

sealed class LeafScreen(private val route: String) {
    fun createRoute(root: Screen) = "${root.route}/$route"

    object Home : LeafScreen(route = Screen.Home.route)
    object Category : LeafScreen(route = Screen.Category.route)
    object Favorite : LeafScreen(route = Screen.Favorite.route)
    object Profile : LeafScreen(route = Screen.Profile.route)

    object Practice : LeafScreen(route = "practice")
}