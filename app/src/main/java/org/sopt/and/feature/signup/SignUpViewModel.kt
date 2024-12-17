package org.sopt.and.feature.signup.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.core.component.BaseViewModel
import org.sopt.and.domain.entity.UserInfo
import org.sopt.and.domain.repository.SignUpRepository
import org.sopt.and.feature.signup.model.SignUpContract.SignUpEvent
import org.sopt.and.feature.signup.model.SignUpContract.SignUpSideEffect
import org.sopt.and.feature.signup.model.SignUpContract.SignUpState
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
) : BaseViewModel<SignUpState, SignUpSideEffect, SignUpEvent>() {

    override fun createInitialState() = SignUpState()

    override suspend fun handleEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.UpdateEmail -> setState { copy(email = event.email) }
            is SignUpEvent.UpdatePassword -> setState { copy(password = event.password) }
            is SignUpEvent.UpdateHobby -> setState { copy(hobby = event.hobby) }
            is SignUpEvent.SubmitSignUp -> submitSignUp()
        }
    }

    private fun submitSignUp() {
        val email = uiState.value.email
        val password = uiState.value.password
        val hobby = uiState.value.hobby

        setState { copy(isLoading = true, errorMessage = null) }
        viewModelScope.launch {
            signUpRepository.postSignUp(UserInfo(email, password, hobby))
                .onSuccess {
                    setSideEffect { SignUpSideEffect.NavigateToLogin }
                }
                .onFailure { exception ->
                    setState {
                        copy(
                            errorMessage = exception.message ?: "오류 발생"
                        )
                    }
                    setSideEffect { SignUpSideEffect.ShowSnackbar("회원가입 실패: ${exception.message}") }
                }
        }
    }
}
