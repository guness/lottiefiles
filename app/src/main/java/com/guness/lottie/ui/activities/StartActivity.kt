package com.guness.lottie.ui.activities

import android.os.Bundle
import com.guness.lottie.core.LottieActivity
import com.guness.lottie.utils.extensions.startActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartActivity : LottieActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity<MainActivity>()
    }
}
