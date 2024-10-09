package org.sopt.and.feature.usecase

import android.util.Patterns

class UserInfoUseCase {
    fun isValidEmail(signUpEmail: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(signUpEmail).matches()
    }

    fun isValidPassword(signUpPassword: String): Boolean {
        val regex =
            Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,20}$")
        return signUpPassword.matches(regex)
    }
}