package org.sopt.and.feature.mypage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import org.sopt.and.core.getSafeParcelable
import org.sopt.and.feature.model.UserInfo
import org.sopt.and.feature.nav.BottomNavGraph
import org.sopt.and.feature.nav.BottomNavigation
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MyPageActivity : ComponentActivity() {
    private lateinit var userInfo: UserInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                userInfo = intent.getSafeParcelable<UserInfo>("userInfo") ?: UserInfo(
                    id = "",
                    password = ""
                )
                MyPageScreen(userInfo)
            }
        }
    }
}


@Composable
fun MyPageScreen(userInfo: UserInfo) {
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
            BottomNavGraph(navController = navController, userInfo = userInfo)
        }
    }
}


@Preview
@Composable
fun GreetingPreview2() {
    ANDANDROIDTheme {
        MyPageScreen(userInfo = UserInfo(id = "dd", password = "sss"))
    }
}