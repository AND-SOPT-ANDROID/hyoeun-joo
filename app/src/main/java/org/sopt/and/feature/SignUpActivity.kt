package org.sopt.and.feature

import android.os.Bundle
import android.util.Log
import android.util.Patterns
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.component.CustomTextField
import org.sopt.and.component.DividerWithText
import org.sopt.and.core.navigateWithUserInfo
import org.sopt.and.core.showToast
import org.sopt.and.feature.model.UserInfo
import org.sopt.and.feature.usecase.UserInfoUseCase
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.util.extenstion.applyColorSpan

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                SignUpScreen()
            }
        }
    }
}

@Composable
fun SignUpScreen() {
    val userInfoUseCase = UserInfoUseCase()
    var signUpEmail by remember { mutableStateOf("") }
    var signUpPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    var userInfo by remember { mutableStateOf<UserInfo?>(null) }

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
                    stringResource(R.string.sign_up),
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
                text = stringResource(R.string.signup_join_with_email_password).applyColorSpan(
                    start = 0,
                    end = 9,
                    color = Color.White,
                ),
            )
            BasicText(
                text = stringResource(R.string.signup_join_with_wavve).applyColorSpan(
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
                stringResource(R.string.signup_id_description),
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
                    placeholder = stringResource(R.string.login_setting_password),
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
                stringResource(R.string.signup_password_description),
                color = Color(0xFFA5A5A5),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.padding(top = 30.dp))
            DividerWithText(stringResource(R.string.login_join_with_social_account))

            Image(
                painter = painterResource(id = R.drawable.ic_social_login),
                contentDescription = "Social Login",
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                stringResource(R.string.login_join_social_account_description),
                color = Color(0xFFA5A5A5),
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        NavigateToLogin{
            if (userInfoUseCase.isValidEmail(signUpEmail) && userInfoUseCase.isValidPassword(signUpPassword)) {
                userInfo = UserInfo(id = signUpEmail, password = signUpPassword)
                navigateWithUserInfo<LoginActivity>(context,userInfo)
            } else {
               context.showToast(context.getString(R.string.signup_login_error_message))
            }
        }

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
        Text(stringResource(R.string.signup_join_wavve))

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ANDANDROIDTheme {
        SignUpScreen()
    }
}