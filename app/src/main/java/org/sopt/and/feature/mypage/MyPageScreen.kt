package org.sopt.and.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import org.sopt.and.feature.model.UserInfo
import org.sopt.and.feature.nav.BottomNavGraph
import org.sopt.and.feature.nav.BottomNavigation
import org.sopt.and.ui.theme.ANDANDROIDTheme


@Composable
fun MyPageScreen(userInfo: UserInfo, viewModel: MyPageViewModel) {
    viewModel.setEmail(userInfo)

    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF1B1B1B))
                .padding(it)
        ) {
            BottomNavGraph(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

@Preview
@Composable
fun GreetingPreview2() {
    ANDANDROIDTheme {
    }
}