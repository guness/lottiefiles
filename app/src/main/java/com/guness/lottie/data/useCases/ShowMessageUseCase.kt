package com.guness.lottie.data.useCases

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * Created by guness on 17.01.2022 01:15
 */
class ShowMessageUseCase(val context: Context, @StringRes val resId: Int) : UseCase<Unit>() {
    override suspend fun invoke() {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
    }
}
