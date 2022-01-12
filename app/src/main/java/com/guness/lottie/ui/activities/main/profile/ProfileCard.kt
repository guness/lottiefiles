package com.guness.lottie.ui.activities.main.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.guness.lottie.R
import com.guness.lottie.ui.theme.*

/**
 * Created by guness on 15.11.2021 01:47
 */
@Composable
fun ProfileCard(onClick: (ProfileItem) -> Unit, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(top = Padding.m)
                    .size(AvatarSize)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.background)
                    .clickable { onClick(ProfileItem.AVATAR) },
                contentAlignment = Alignment.Center

            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = stringResource(R.string.profile_photo),
                    modifier = Modifier.size(Padding.xl)
                )
            }

            Title1(
                /*TODO*/
                text = "Sinan",
                modifier = Modifier.padding(top = Padding.xs, bottom = Padding.m)
            )
            ListItem(
                modifier = Modifier.clickable { onClick(ProfileItem.NAME) },
                secondaryText = { Caption2(text = "Sinan" /*TODO*/) },
                text = { Body2(text = stringResource(R.string.profile_name)) }
            )
            ListItem(
                modifier = Modifier.clickable { onClick(ProfileItem.ACCOUNT) },
                secondaryText = { Caption2(text = stringResource(R.string.profile_free)  /*TODO*/) },
                text = { Body2(text = stringResource(R.string.profile_account_type)) }
            )
            ListItem(
                modifier = Modifier.clickable { onClick(ProfileItem.REMAINDER) },
                text = { Body2(text = stringResource(R.string.profile_daily_remainder)) })
            ListItem(
                modifier = Modifier.clickable { onClick(ProfileItem.SETTINGS) },
                text = { Body2(text = stringResource(R.string.profile_quick_start)) })
            ListItem(
                modifier = Modifier
                    .clickable { onClick(ProfileItem.APPEARANCE) }
                    .padding(bottom = Padding.xs),
                secondaryText = { Caption2(text = stringResource(R.string.profile_system)  /*TODO*/) },
                text = { Body2(text = stringResource(R.string.profile_appearance)) }
            )
        }
    }
}

@Preview(widthDp = 340)
@Composable
private fun CardPreview() = LottieTheme {
    ProfileCard({})
}
