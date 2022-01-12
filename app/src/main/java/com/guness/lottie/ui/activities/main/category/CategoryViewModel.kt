package com.guness.lottie.ui.activities.main.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guness.lottie.data.dto.Category
import com.guness.lottie.data.useCases.AnimatorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by guness on 8.11.2021 11:42
 */
@HiltViewModel
class CategoryViewModel @Inject constructor(private val animatorsUseCase: AnimatorsUseCase) : ViewModel() {

    val categories = Category.values().toList()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            animatorsUseCase.update()
        }
    }
}
