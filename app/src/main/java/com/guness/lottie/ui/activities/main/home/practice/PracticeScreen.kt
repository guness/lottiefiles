package com.guness.lottie.ui.activities.main.home.practice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.guness.lottie.ui.theme.LottieTheme
import com.guness.lottie.utils.widget.TopBackground

/**
 * Created by guness on 21.11.2021 11:37
 */
@Composable
fun PracticeScreen(viewModel: PracticeViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        TopBackground()
        Text(
            text = "Practice",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PracticePreview() = LottieTheme {
    PracticeScreen(PracticeViewModel())
}
