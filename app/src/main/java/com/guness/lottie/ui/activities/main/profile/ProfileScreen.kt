package com.guness.lottie.ui.activities.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.guness.lottie.R
import com.guness.lottie.data.dto.User
import com.guness.lottie.ui.theme.BottomBarHeight
import com.guness.lottie.ui.theme.LargeTitle
import com.guness.lottie.ui.theme.LottieTheme
import com.guness.lottie.ui.theme.Padding
import com.guness.lottie.utils.OnClick
import com.guness.lottie.utils.widget.TopBackground
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val user by viewModel.user.collectAsState(initial = null)

    val scope = rememberCoroutineScope()

    ScreenContent(user,
        onLoginClick = {
            scope.launch { viewModel.login("Sinan") }
        },
        onLogoutClick = {
            scope.launch { viewModel.logout() }
        })
}

@Composable
private fun ScreenContent(user: User?, onLoginClick: OnClick<Unit> = {}, onLogoutClick: OnClick<Unit> = {}) {
    val onProfileClick = { item: ProfileItem ->
        when (item) {
            ProfileItem.AVATAR -> Unit /*TODO*/
            ProfileItem.NAME -> Unit /*TODO*/
            ProfileItem.ACCOUNT -> Unit /*TODO*/
            ProfileItem.REMAINDER -> Unit /*TODO*/
            ProfileItem.SETTINGS -> Unit /*TODO*/
            ProfileItem.APPEARANCE -> Unit /*TODO*/
            ProfileItem.LOGIN -> onLoginClick(Unit)
            ProfileItem.LOGOUT -> onLogoutClick(Unit)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        TopBackground()
        LazyColumn(contentPadding = PaddingValues(bottom = BottomBarHeight)) {
            item {
                LargeTitle(
                    text = stringResource(id = R.string.nav_profile),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Padding.s, top = Padding.m, end = Padding.xxl, bottom = Padding.m)
                )
            }
            item {
                ProfileCard(
                    user = user,
                    onClick = onProfileClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Padding.s, end = Padding.s, bottom = Padding.m)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfilePreview() = LottieTheme {
    ScreenContent(null)
}
