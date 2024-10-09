package org.sopt.and.feature.nav

import androidx.annotation.DrawableRes
import org.sopt.and.R
import org.sopt.and.util.Route

sealed class BottomNavItem(
    val route: Int,
    @DrawableRes val icon: Int,
    val screenRoute: String
) {
    data object Home :
        BottomNavItem(
            R.string.nav_home,
            R.drawable.ic_home_24,
            Route.HOME
        )

    data object Search :
        BottomNavItem(
            R.string.nav_search,
            R.drawable.ic_search_24,
            Route.SEARCH
        )

    data object MyPage :
        BottomNavItem(
            R.string.nav_my_page,
            R.drawable.ic_profile_24,
            Route.MY_PAGE
        )
}