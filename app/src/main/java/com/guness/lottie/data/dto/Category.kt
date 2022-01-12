package com.guness.lottie.data.dto

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.guness.lottie.R

/**
 * Created by guness on 8.11.2021 10:17
 */
enum class Category(@DrawableRes val icon: Int, @StringRes val text: Int) {
    GENERAL(R.drawable.ic_cat_books, R.string.cat_general),
    ACCEPTANCE(R.drawable.ic_cat_person, R.string.cat_acceptance),
    COMMUNICATION(R.drawable.ic_cat_chat, R.string.cat_communication),
    HEALTH(R.drawable.ic_cat_health, R.string.cat_health),
    LOVE(R.drawable.ic_cat_heart, R.string.cat_love),
    MOTIVATION(R.drawable.ic_cat_rocket, R.string.cat_motivation),
    STRESS(R.drawable.ic_cat_nervous, R.string.cat_stress),
    SUCCESS(R.drawable.ic_cat_medal, R.string.cat_success),
    WEALTH(R.drawable.ic_cat_money, R.string.cat_wealth),
}
