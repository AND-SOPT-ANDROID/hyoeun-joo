package org.sopt.and.feature.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.and.feature.home.HomeScreen
import org.sopt.and.feature.mypage.MyPageViewModel
import org.sopt.and.feature.mypage.ProfileScreen
import org.sopt.and.feature.search.SearchScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    viewModel: MyPageViewModel,
) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.screenRoute) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen()
        }
        composable(BottomNavItem.Search.screenRoute) {
            SearchScreen()
        }
        composable(BottomNavItem.MyPage.screenRoute) {
            ProfileScreen(viewModel = viewModel)
        }
    }
}