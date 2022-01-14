package com.guness.lottie.ui.activities.main

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.guness.lottie.R
import com.guness.lottie.core.navigation.LottieNavigation
import com.guness.lottie.core.navigation.Screen
import com.guness.lottie.ui.theme.Radius
import kotlinx.coroutines.launch

private val items = listOf(
    BottomBarItem(Screen.Home, R.string.nav_home, R.drawable.ic_home, R.drawable.ic_home_filled),
    BottomBarItem(Screen.Popular, R.string.nav_popular, R.drawable.ic_heart, R.drawable.ic_heart_filled),
    BottomBarItem(Screen.Recent, R.string.nav_recent, R.drawable.ic_category, R.drawable.ic_category_filled),
    BottomBarItem(Screen.Profile, R.string.nav_profile, R.drawable.ic_profile, R.drawable.ic_profile_filled)
)

private data class BottomBarItem(val screen: Screen, @StringRes val title: Int, @DrawableRes val icon: Int, @DrawableRes val selected: Int)

@Composable
fun BottomBar(navController: NavController) {
    val shape = RoundedCornerShape(topStart = Radius.l, topEnd = Radius.l)
    BottomNavigation(
        modifier = Modifier.clip(shape),
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach {
            val selected = currentRoute == it.screen.route
            val title = stringResource(it.title)
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = if (selected) it.selected else it.icon),
                        contentDescription = title,
                    )
                },
                label = { Text(text = title) },
                selected = selected,
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.primaryVariant,
                onClick = {
                    navController.navigate(it.screen.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
fun BottomBarMain(navController: NavHostController, bottomSheetState: ModalBottomSheetState) {
    val scope = rememberCoroutineScope()

    BackHandler(bottomSheetState.isVisible) {
        scope.launch { bottomSheetState.hide() }
    }

    LottieNavigation(navController = navController, onSubscribe = {
        scope.launch { bottomSheetState.animateTo(ModalBottomSheetValue.Expanded) }
    })
}
