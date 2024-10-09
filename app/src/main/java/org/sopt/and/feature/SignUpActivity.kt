package org.sopt.and.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.component.CustomTextField
import org.sopt.and.component.DividerWithText
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.util.extenstion.applyColorSpan

class SignUpActivity : ComponentActivity() {
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
fun SignUpScreen() {
    var signUpEmail by remember { mutableStateOf("") }
    var signUpPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF1B1B1B))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .background(color = Color(0xFF1B1B1B))
            ) {
                Text(
                    "회원가입",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_close_white_24),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(vertical = 16.dp)
                )

            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
            BasicText(
                text = "이메일과 비밀번호만으로".applyColorSpan(
                    start = 0,
                    end = 9,
                    color = Color.White,
                ),
            )
            BasicText(
                text = "Wavve를 즐길 수 있어요!".applyColorSpan(
                    start = 0,
                    end = 11,
                    color = Color.White,
                )
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            CustomTextField(
                value = signUpEmail,
                onValueChange = { signUpEmail = it },
                placeholder = "wavve@example.com"
            )
            Text(
                "! 로그인,비밀번호 찾기,알림에 사용되니 정확한 이메일을 입력해주세요.",
                color = Color(0xFFA5A5A5),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                CustomTextField(
                    value = signUpPassword,
                    onValueChange = { signUpPassword = it },
                    placeholder = "Wavve 비밀번호 설정",
                    passwordVisible = passwordVisible
                )
                Text(
                    text = if (passwordVisible) "hide" else "show",
                    color = Color.White,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .clickable { passwordVisible = !passwordVisible }
                )
            }
            Text(
                "! 비밀번호는 8~20자 이내로 영문 대소문자,숫자, 특수문자 중 3가지 이상 혼영하여 입력해 주세요.",
                color = Color(0xFFA5A5A5),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.padding(top = 30.dp))
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
        Spacer(modifier = Modifier.weight(1f))

        NavigateToLogin{}

    }

}


@Composable
fun NavigateToLogin(onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonColors(
            contentColor = Color.White,
            containerColor = Color(0xFF717171),
            disabledContentColor = Color(0xFF717171),
            disabledContainerColor = Color(0xFF717171),
        ),
        shape = RectangleShape,
        contentPadding = PaddingValues(16.dp)

    ) {
        Text("Wavve 회원가입")

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ANDANDROIDTheme {
        SignUpScreen()
    }
}