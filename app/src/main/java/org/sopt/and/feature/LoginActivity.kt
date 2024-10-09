package org.sopt.and.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.component.CustomTextField
import org.sopt.and.component.DividerWithText
import org.sopt.and.ui.theme.ANDANDROIDTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {

            }
        }
    }
}

@Composable
fun LoginScreen() {
    var logInId by remember { mutableStateOf("") }
    var logInPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF1B1B1B))
            .padding(horizontal = 10.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .background(color = Color(0xFF1B1B1B))
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back_left_white_24),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(vertical = 16.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Logo",

                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .aspectRatio(264f / 116f)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.padding(top = 30.dp))
        CustomTextField(value = logInId, onValueChange = { logInId = it }, "이메일 주소 또는 아이디")
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            CustomTextField(
                value = logInPassword,
                onValueChange = { logInPassword = it },
                placeholder = "Wavve 비밀번호 설정",
                passwordVisible = passwordVisible,
                padding = PaddingValues(vertical = 10.dp)
            )
            Text("show", color = Color.White,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable { passwordVisible = !passwordVisible }
            )
        }
        Spacer(modifier = Modifier.padding(top = 30.dp))
        NavigateToMain {  }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        ThreeTextsWithDividers(modifier = Modifier.fillMaxWidth(), "아이디 찾기", "비밀번호 재설정", "회원가입")
        DividerWithText("또는 다른 서비스 계정으로 가입")
        Image(
            painter = painterResource(id = R.drawable.ic_social_login),
            contentDescription = "Social Login",
        )
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Text(
            "SNS계정으로 간편하게 가입하여 서비스를 이용하실 수 있습니다. 기존 POOQ 계정 또는 Wavve 계정과는 연동되지 않으니 이용에 참고하세요.",
            color = Color(0xFFA5A5A5),
            fontSize = 12.sp
        )
    }
}
@Composable
fun NavigateToMain(onClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color(0xFF1352F9),
            disabledContentColor = Color(0xFF1352F9),
            disabledContainerColor = Color(0xFF1352F9)
        ),
        contentPadding = PaddingValues(16.dp)
    ) {
        Text("로그인")
    }
}

@Composable
fun ThreeTextsWithDividers(
    modifier: Modifier = Modifier,
    text1: String,
    text2: String,
    text3: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text1,
            color = Color(0xFFA5A5A5),
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.width(8.dp))

        VerticalDivider(
            color = Color(0xFFA5A5A5),
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text2,
            color = Color(0xFFA5A5A5),
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.width(8.dp))

        VerticalDivider(
            color = Color(0xFFA5A5A5),
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text3,
            color = Color(0xFFA5A5A5),
            fontSize = 12.sp
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ExamplePreview() {
    ANDANDROIDTheme {
        LoginScreen()
    }
}