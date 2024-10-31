package org.sopt.and.feature.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sopt.and.feature.model.UserInfo

class LoginViewModel : ViewModel() {

    val email: StateFlow<String>
        field = MutableStateFlow("")

    val password: StateFlow<String>
        field = MutableStateFlow("")

    val isLoginSuccessful: StateFlow<Boolean>
        field = MutableStateFlow(false)

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun login(userInfo: UserInfo?) {
        val currentEmail = email.value
        val currentPassword = password.value

        if (currentEmail.isNotBlank() && currentPassword.isNotBlank() &&
            currentEmail == userInfo?.id && currentPassword == userInfo.password
        ) {
            isLoginSuccessful.value = true
        } else {
            isLoginSuccessful.value = false
        }
    }
}
