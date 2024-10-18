package org.sopt.and.feature.home

import androidx.annotation.DrawableRes

data class HomeBannerItem(
    @DrawableRes val imageResId: Int,
    val title: String,
    val description: String,
)
