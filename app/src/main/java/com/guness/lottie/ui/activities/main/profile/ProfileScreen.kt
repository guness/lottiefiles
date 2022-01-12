package com.guness.lottie.ui.activities.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.guness.lottie.R
import com.guness.lottie.ui.theme.BottomBarHeight
import com.guness.lottie.ui.theme.LargeTitle
import com.guness.lottie.ui.theme.Padding
import com.guness.lottie.ui.theme.LottieTheme
import com.guness.lottie.utils.OnClick
import com.guness.lottie.utils.widget.TopBackground

@Composable
fun ProfileScreen(onSubscribe: OnClick, viewModel: ProfileViewModel = hiltViewModel()) {
    val onProfileClick = { item: ProfileItem ->
        when (item) {
            ProfileItem.AVATAR -> Unit /*TODO*/
            ProfileItem.NAME -> Unit /*TODO*/
            ProfileItem.ACCOUNT -> Unit /*TODO*/
            ProfileItem.REMAINDER -> onSubscribe()
            ProfileItem.SETTINGS -> Unit /*TODO*/
            ProfileItem.APPEARANCE -> onSubscribe()
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
                    onClick = onProfileClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Padding.s, end = Padding.s, bottom = Padding.m)
                )
            }

            item {
                MoodTrackerCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Padding.s, end = Padding.s, bottom = Padding.m)
                )
            }

            item {
                AchievementsCard(
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
    ProfileScreen({}, ProfileViewModel())
}
