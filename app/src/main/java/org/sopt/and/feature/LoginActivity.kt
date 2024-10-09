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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.component.CustomTextField
import org.sopt.and.component.DividerWithText
import org.sopt.and.core.getSafeParcelable
import org.sopt.and.core.navigateWithUserInfo
import org.sopt.and.feature.model.UserInfo
import org.sopt.and.feature.mypage.MyPageActivity
import org.sopt.and.ui.theme.ANDANDROIDTheme

class LoginActivity : ComponentActivity() {
    private lateinit var userInfo: UserInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        userInfo = intent.getSafeParcelable<UserInfo>("userInfo") ?: UserInfo(
            id = "",
            password = ""
        )

        setContent {
            ANDANDROIDTheme {
                LoginScreen(userInfo)

            }
        }
    }
}

@Composable
fun LoginScreen(userInfo: UserInfo?) {
    var logInEmail by remember { mutableStateOf("") }
    var logInPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val snackbarHostState = remember { SnackbarHostState() }

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
        CustomTextField(
            value = logInEmail, onValueChange = { logInEmail = it },
            stringResource(R.string.login_email_id)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            CustomTextField(
                value = logInPassword,
                onValueChange = { logInPassword = it },
                placeholder = stringResource(R.string.login_setting_password),
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
        NavigateToMain {
            if (logInEmail.isNotBlank() && logInPassword.isNotBlank() && logInEmail == userInfo?.id && logInPassword == userInfo.password) {

                navigateWithUserInfo<MyPageActivity>(context, userInfo)
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    snackbarHostState.showSnackbar(context.getString(R.string.login_no_member_info))
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        ThreeTextsWithDividers(
            modifier = Modifier.fillMaxWidth(),
            stringResource(R.string.login_find_id),
            stringResource(R.string.login_setting_password_again), stringResource(R.string.sign_up)
        )
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
        SnackbarHost(hostState = snackbarHostState)

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
        Text(stringResource(R.string.login))
    }
}

@Composable
fun ThreeTextsWithDividers(
    modifier: Modifier = Modifier,
    text1: String,
    text2: String,
    text3: String,
) {
    val context = LocalContext.current

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
            fontSize = 12.sp,
            modifier = Modifier.clickable {
                navigateWithUserInfo<SignUpActivity>(context)

            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ExamplePreview() {
    ANDANDROIDTheme {
        LoginScreen(userInfo = null)
    }
}