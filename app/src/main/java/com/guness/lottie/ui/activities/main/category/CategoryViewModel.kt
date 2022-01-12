package com.guness.lottie.ui.activities.main.category

import androidx.lifecycle.ViewModel
import com.guness.lottie.data.dto.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by guness on 8.11.2021 11:42
 */
@HiltViewModel
class CategoryViewModel @Inject constructor() : ViewModel() {

    val categories = Category.values().toList()
}
