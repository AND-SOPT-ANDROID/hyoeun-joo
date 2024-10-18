package org.sopt.and.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.and.feature.login.LoginScreen
import org.sopt.and.feature.model.UserInfo
import org.sopt.and.feature.mypage.MyPageScreen
import org.sopt.and.feature.mypage.MyPageViewModel
import org.sopt.and.feature.signup.SignUpScreen
import org.sopt.and.ui.theme.ANDANDROIDTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ANDANDROIDTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        LoginScreen(navController)
                    }
                    composable("signup") {
                        SignUpScreen(navController)
                    }
                    composable("mypage") {
                        val userInfo =
                            navController.previousBackStackEntry?.arguments?.getParcelable<UserInfo>(
                                "userInfo"
                            )
                        if (userInfo != null) {
                            MyPageScreen(userInfo = userInfo, viewModel = MyPageViewModel())
                        }
                    }
                }
            }
        }
    }
}
