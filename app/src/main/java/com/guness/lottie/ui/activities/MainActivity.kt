package com.guness.lottie.ui.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.guness.lottie.core.LottieActivity
import com.guness.lottie.ui.activities.animation.AnimationScreen
import com.guness.lottie.ui.activities.main.BottomBar
import com.guness.lottie.ui.activities.main.BottomBarMain
import com.guness.lottie.ui.theme.Radius
import com.guness.lottie.ui.theme.LottieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : LottieActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LottieTheme(darkTheme = arrayOf(true, false).random()) {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainView()
                }
            }
        }
    }
}

@Composable
fun MainView() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = {
            it != ModalBottomSheetValue.HalfExpanded
        }
    )

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            AnimationScreen() },
        sheetShape = RoundedCornerShape(topStart = Radius.l, topEnd = Radius.l)
    ) {
        Scaffold(
            bottomBar = {
                navBackStackEntry?.destination?.route?.let { route ->
                    val paths = route.split("/")
                    if (paths.size == 2 && paths.getOrElse(0) { "/" } == paths.getOrNull(1)) {
                        BottomBar(navController)
                    }
                }
            }
        ) {
            BottomBarMain(navController, modalBottomSheetState)
        }
    }
}
