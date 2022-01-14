package com.guness.lottie.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.guness.lottie.ui.activities.main.recent.RecentScreen
import com.guness.lottie.ui.activities.main.home.HomeScreen
import com.guness.lottie.ui.activities.main.home.practice.PracticeScreen
import com.guness.lottie.ui.activities.main.popular.PopularScreen
import com.guness.lottie.ui.activities.main.profile.ProfileScreen
import com.guness.lottie.utils.OnClick

/**
 * Created by guness on 21.11.2021 15:25
 */
// inherited from: https://github.com/chrisbanes/tivi/blob/main/app/src/main/java/app/tivi/AppNavigation.kt
@Composable
internal fun LottieNavigation(
    navController: NavHostController,
    onSubscribe: OnClick,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier,
    ) {
        addTopLevelHome(navController, onSubscribe)
        addTopLevelPopular(onSubscribe)
        addTopLevelRecent(onSubscribe)
        addTopLevelProfile(onSubscribe)
    }
}

private fun NavGraphBuilder.addTopLevelHome(navController: NavController, onSubscribe: OnClick) {
    navigation(
        route = Screen.Home.route,
        startDestination = LeafScreen.Home.createRoute(Screen.Home),
    ) {
        addHome(navController, Screen.Home, onSubscribe)
        addPractice(Screen.Home)
    }
}

private fun NavGraphBuilder.addTopLevelRecent(onSubscribe: OnClick) {
    navigation(
        route = Screen.Recent.route,
        startDestination = LeafScreen.Recent.createRoute(Screen.Recent),
    ) {
        addRecent(Screen.Recent, onSubscribe)
    }
}

private fun NavGraphBuilder.addTopLevelPopular(onSubscribe: OnClick) {
    navigation(
        route = Screen.Popular.route,
        startDestination = LeafScreen.Popular.createRoute(Screen.Popular),
    ) {
        addPopular(Screen.Popular, onSubscribe)
    }
}

private fun NavGraphBuilder.addTopLevelProfile(onSubscribe: OnClick) {
    navigation(
        route = Screen.Profile.route,
        startDestination = LeafScreen.Profile.createRoute(Screen.Profile),
    ) {
        addProfile(Screen.Profile, onSubscribe)
    }
}

private fun NavGraphBuilder.addHome(
    navController: NavController,
    root: Screen,
    onSubscribe: OnClick
) {
    composable(LeafScreen.Home.createRoute(root)) {
        HomeScreen(
            onPractice = {
                navController.navigate(LeafScreen.Practice.createRoute(root))
            },
            onSubscribe = onSubscribe
        )
    }
}

private fun NavGraphBuilder.addRecent(
    root: Screen,
    onSubscribe: OnClick
) {
    composable(LeafScreen.Recent.createRoute(root)) {
        RecentScreen(onSubscribe = onSubscribe)
    }
}

private fun NavGraphBuilder.addPopular(
    root: Screen,
    onSubscribe: OnClick
) {
    composable(LeafScreen.Popular.createRoute(root)) {
        PopularScreen(onSubscribe = onSubscribe)
    }
}

private fun NavGraphBuilder.addProfile(
    root: Screen,
    onSubscribe: OnClick
) {
    composable(LeafScreen.Profile.createRoute(root)) {
        ProfileScreen(onSubscribe = onSubscribe)
    }
}

private fun NavGraphBuilder.addPractice(
    root: Screen,
) {
    composable(LeafScreen.Practice.createRoute(root)) {
        PracticeScreen()
    }
}
