package com.guness.lottie.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.guness.lottie.ui.activities.main.home.HomeScreen
import com.guness.lottie.ui.activities.main.home.practice.PracticeScreen
import com.guness.lottie.ui.activities.main.popular.PopularScreen
import com.guness.lottie.ui.activities.main.profile.ProfileScreen
import com.guness.lottie.ui.activities.main.recent.RecentScreen
import com.guness.lottie.utils.OnClick

/**
 * Created by guness on 21.11.2021 15:25
 */
// inherited from: https://github.com/chrisbanes/tivi/blob/main/app/src/main/java/app/tivi/AppNavigation.kt
@Composable
internal fun LottieNavigation(
    navController: NavHostController,
    onAnimationClick: OnClick<Long>,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier,
    ) {
        addTopLevelHome(navController, onAnimationClick)
        addTopLevelPopular(onAnimationClick)
        addTopLevelRecent(onAnimationClick)
        addTopLevelProfile(onAnimationClick)
    }
}

private fun NavGraphBuilder.addTopLevelHome(navController: NavController, onAnimationClick: OnClick<Long>) {
    navigation(
        route = Screen.Home.route,
        startDestination = LeafScreen.Home.createRoute(Screen.Home),
    ) {
        addHome(navController, Screen.Home, onAnimationClick)
        addPractice(Screen.Home)
    }
}

private fun NavGraphBuilder.addTopLevelRecent(onAnimationClick: OnClick<Long>) {
    navigation(
        route = Screen.Recent.route,
        startDestination = LeafScreen.Recent.createRoute(Screen.Recent),
    ) {
        addRecent(Screen.Recent, onAnimationClick)
    }
}

private fun NavGraphBuilder.addTopLevelPopular(onAnimationClick: OnClick<Long>) {
    navigation(
        route = Screen.Popular.route,
        startDestination = LeafScreen.Popular.createRoute(Screen.Popular),
    ) {
        addPopular(Screen.Popular, onAnimationClick)
    }
}

private fun NavGraphBuilder.addTopLevelProfile(onAnimationClick: OnClick<Long>) {
    navigation(
        route = Screen.Profile.route,
        startDestination = LeafScreen.Profile.createRoute(Screen.Profile),
    ) {
        addProfile(Screen.Profile)
    }
}

private fun NavGraphBuilder.addHome(
    navController: NavController,
    root: Screen,
    onAnimationClick: OnClick<Long>
) {
    composable(LeafScreen.Home.createRoute(root)) {
        HomeScreen(onAnimationClick = onAnimationClick)
    }
}

private fun NavGraphBuilder.addRecent(
    root: Screen,
    onAnimationClick: OnClick<Long>
) {
    composable(LeafScreen.Recent.createRoute(root)) {
        RecentScreen(onAnimationClick = onAnimationClick)
    }
}

private fun NavGraphBuilder.addPopular(
    root: Screen,
    onAnimationClick: OnClick<Long>
) {
    composable(LeafScreen.Popular.createRoute(root)) {
        PopularScreen(onAnimationClick = onAnimationClick)
    }
}

private fun NavGraphBuilder.addProfile(
    root: Screen
) {
    composable(LeafScreen.Profile.createRoute(root)) {
        ProfileScreen()
    }
}

private fun NavGraphBuilder.addPractice(
    root: Screen,
) {
    composable(LeafScreen.Practice.createRoute(root)) {
        PracticeScreen()
    }
}
