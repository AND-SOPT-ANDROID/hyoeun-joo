package org.sopt.and.feature.login.model

import org.sopt.and.core.component.UiEvent
import org.sopt.and.core.component.UiSideEffect
import org.sopt.and.core.component.UiState

class LoginContract {
    data class LoginState(
        val email: String = "",
        val password: String = "",
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    ) : UiState

    sealed class LoginEvent : UiEvent {
        data class UpdateEmail(val email: String) : LoginEvent()
        data class UpdatePassword(val password: String) : LoginEvent()
        data class SubmitLogin(val email: String, val password: String) : LoginEvent()
    }

    sealed class LoginSideEffect : UiSideEffect {
        data object NavigateToMyPage : LoginSideEffect()
        data class ShowSnackbar(val message: String) : LoginSideEffect()
    }
}
