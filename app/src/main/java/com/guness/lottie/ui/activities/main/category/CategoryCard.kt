package com.guness.lottie.ui.activities.main.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.guness.lottie.data.dto.Category
import com.guness.lottie.ui.theme.Body1
import com.guness.lottie.ui.theme.Padding
import com.guness.lottie.ui.theme.LottieTheme
import com.guness.lottie.utils.OnClick

/**
 * Created by guness on 8.11.2021 11:59
 */

@Composable
fun CategoryCard(modifier: Modifier = Modifier, category: Category, onCategoryClick: OnClick = {}) {
    Card(
        modifier = modifier.aspectRatio(1f)
    ) {
        Column(
            modifier = Modifier.clickable(onClick = onCategoryClick),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = category.icon),
                contentDescription = stringResource(category.text),
                modifier = Modifier.size(Padding.xxl)
            )
            Body1(
                text = stringResource(category.text),
            )
        }
    }
}

@Preview(widthDp = 164)
@Composable
private fun CardPreview() = LottieTheme {
    CategoryCard(category = Category.COMMUNICATION)
}