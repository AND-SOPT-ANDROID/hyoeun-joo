package org.sopt.and.feature.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import org.sopt.and.R
import org.sopt.and.core.component.DescriptionText
import org.sopt.and.core.component.DividerWithText
import org.sopt.and.core.component.textfield.CustomEmailTextField
import org.sopt.and.core.component.textfield.CustomPwTextField
import org.sopt.and.feature.signup.model.SignUpContract
import org.sopt.and.feature.signup.viewmodel.SignUpViewModel
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.util.extenstion.applyColorSpan


@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    var passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                is SignUpContract.SignUpSideEffect.NavigateToLogin -> {
                    navController.navigate("login") {
                        popUpTo("signup") { inclusive = true }
                    }
                }

                is SignUpContract.SignUpSideEffect.ShowSnackbar -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF1B1B1B))
            .padding(horizontal = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            SignUpTopBar()
            Spacer(modifier = Modifier.height(10.dp))

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
            Spacer(modifier = Modifier.height(20.dp))

            CustomEmailTextField(
                value = uiState.email,
                onValueChange = { viewModel.setEvent(SignUpContract.SignUpEvent.UpdateEmail(it)) },
                placeholder = "wavve@example.com"
            )
            DescriptionText(stringResource(R.string.signup_id_description))

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                CustomPwTextField(
                    value = uiState.password,
                    onValueChange = {
                        viewModel.setEvent(
                            SignUpContract.SignUpEvent.UpdatePassword(
                                it
                            )
                        )
                    },
                    placeholder = stringResource(R.string.login_setting_password)
                )
                Text(
                    text = if (passwordVisible) "hide" else "show",
                    color = Color.White,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .clickable { passwordVisible = !passwordVisible }
                )
            }
            DescriptionText(stringResource(R.string.signup_password_description))

            CustomEmailTextField(
                value = uiState.hobby,
                onValueChange = { viewModel.setEvent(SignUpContract.SignUpEvent.UpdateHobby(it)) },
                placeholder = "취미를 적어주세요"
            )
            DescriptionText("취미는 8자 이하여야 합니다")
            Spacer(modifier = Modifier.height(30.dp))

            DividerWithText(stringResource(R.string.login_join_with_social_account))
            Image(
                painter = painterResource(id = R.drawable.ic_social_login),
                contentDescription = "Social Login"
            )

            Spacer(modifier = Modifier.height(20.dp))
            DescriptionText(stringResource(R.string.login_join_social_account_description))
        }

        NavigateToLogin(
            backgroundColor = buttonEnableBackgroundColor(uiState.email, uiState.password)
        ) {
            viewModel.setEvent(SignUpContract.SignUpEvent.SubmitSignUp)
        }

    }
}

@Composable
fun SignUpTopBar() {
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
}

@Composable
fun NavigateToLogin(backgroundColor: Color, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonColors(
            contentColor = Color.White,
            containerColor = backgroundColor,
            disabledContentColor = Color(0xFF717171),
            disabledContainerColor = Color(0xFF717171),
        ),
        shape = RectangleShape,
        contentPadding = PaddingValues(16.dp)

    ) {
        Text(stringResource(R.string.signup_join_wavve))

    }
}

private fun buttonEnableBackgroundColor(signUpEmail: String, signUpPassword: String): Color {
    return if (signUpEmail.isNotEmpty() && signUpPassword.isNotEmpty()) {
        Color(0xFF1352F9)
    } else {
        Color(0xFF717171)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ANDANDROIDTheme {
    }
}
