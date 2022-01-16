package com.guness.lottie.data.useCases

import android.content.Context
import android.content.Intent
import com.guness.lottie.data.dto.Animation

/**
 * Created by guness on 17.01.2022 01:15
 */
class ShareAnimationUseCase(val context: Context, val animation: Animation) : UseCase<Unit>() {
    override suspend fun invoke() {
        val share = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/*"
            putExtra(Intent.EXTRA_TEXT, animation.lottieUrl)
            putExtra(Intent.EXTRA_TITLE, animation.name)
        }, null)
        context.startActivity(share)
    }
}
