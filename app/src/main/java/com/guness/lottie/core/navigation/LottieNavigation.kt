package com.guness.lottie.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.guness.lottie.ui.activities.main.category.CategoryScreen
import com.guness.lottie.ui.activities.main.favorite.FavoriteScreen
import com.guness.lottie.ui.activities.main.home.HomeScreen
import com.guness.lottie.ui.activities.main.home.practice.PracticeScreen
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
        addTopLevelCategory(onSubscribe)
        addTopLevelFavorite(onSubscribe)
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

private fun NavGraphBuilder.addTopLevelCategory(onSubscribe: OnClick) {
    navigation(
        route = Screen.Category.route,
        startDestination = LeafScreen.Category.createRoute(Screen.Category),
    ) {
        addCategory(Screen.Category, onSubscribe)
    }
}

private fun NavGraphBuilder.addTopLevelFavorite(onSubscribe: OnClick) {
    navigation(
        route = Screen.Favorite.route,
        startDestination = LeafScreen.Favorite.createRoute(Screen.Favorite),
    ) {
        addFavorite(Screen.Favorite, onSubscribe)
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

private fun NavGraphBuilder.addCategory(
    root: Screen,
    onSubscribe: OnClick
) {
    composable(LeafScreen.Category.createRoute(root)) {
        CategoryScreen(onSubscribe = onSubscribe)
    }
}

private fun NavGraphBuilder.addFavorite(
    root: Screen,
    onSubscribe: OnClick
) {
    composable(LeafScreen.Favorite.createRoute(root)) {
        FavoriteScreen(onSubscribe = onSubscribe)
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
