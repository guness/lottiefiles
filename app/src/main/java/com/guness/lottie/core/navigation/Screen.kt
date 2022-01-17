package com.guness.lottie.core.navigation

/**
 * Created by guness on 21.11.2021 15:29
 */
sealed class Screen(val route: String) {
    object Home : Screen(route = "home")
    object Popular : Screen(route = "popular")
    object Recent : Screen(route = "recent")
    object Profile : Screen(route = "profile")
}

sealed class LeafScreen(private val route: String) {
    fun createRoute(root: Screen) = "${root.route}/$route"

    object Home : LeafScreen(route = Screen.Home.route)
    object Popular : LeafScreen(route = Screen.Popular.route)
    object Recent : LeafScreen(route = Screen.Recent.route)
    object Profile : LeafScreen(route = Screen.Profile.route)
}