package org.sopt.and.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.and.feature.login.LoginScreen
import org.sopt.and.feature.mypage.MyPageScreen
import org.sopt.and.feature.signup.SignUpScreen
import org.sopt.and.ui.theme.ANDANDROIDTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ANDANDROIDTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable(ScreenRoute.LOGIN.route) {
                        LoginScreen(navController)
                    }
                    composable(ScreenRoute.SIGNUP.route) {
                        SignUpScreen(navController)
                    }
                    composable(ScreenRoute.MYPAGE.route) {
                        MyPageScreen()
                    }
                }
            }
        }
    }
}
