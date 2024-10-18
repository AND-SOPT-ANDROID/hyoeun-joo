package org.sopt.and.feature.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.and.feature.model.UserInfo

class LoginViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _isLoginSuccessful = MutableStateFlow<Boolean?>(null)
    val isLoginSuccessful = _isLoginSuccessful.asStateFlow()

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun login(userInfo: UserInfo?) {
        val currentEmail = _email.value
        val currentPassword = _password.value

        if (currentEmail.isNotBlank() && currentPassword.isNotBlank() &&
            currentEmail == userInfo?.id && currentPassword == userInfo.password
        ) {
            _isLoginSuccessful.value = true
        } else {
            _isLoginSuccessful.value = false
        }
    }
}
