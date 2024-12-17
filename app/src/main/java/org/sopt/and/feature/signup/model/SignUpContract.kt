package org.sopt.and.feature.signup.model

import org.sopt.and.core.component.UiEvent
import org.sopt.and.core.component.UiSideEffect
import org.sopt.and.core.component.UiState

class SignUpContract {
    data class SignUpState(
        val email: String = "",
        val password: String = "",
        val hobby: String = "",
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    ) : UiState

    sealed class SignUpEvent : UiEvent {
        data class UpdateEmail(val email: String) : SignUpEvent()
        data class UpdatePassword(val password: String) : SignUpEvent()
        data class UpdateHobby(val hobby: String) : SignUpEvent()
        data object SubmitSignUp : SignUpEvent()
    }

    sealed class SignUpSideEffect:UiSideEffect {
        data object NavigateToLogin : SignUpSideEffect()
        data class ShowSnackbar(val message: String) : SignUpSideEffect()
    }
}
