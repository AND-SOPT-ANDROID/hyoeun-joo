package org.sopt.and.feature.login.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.core.component.BaseViewModel
import org.sopt.and.domain.entity.LoginInfo
import org.sopt.and.domain.repository.LoginRepository
import org.sopt.and.feature.login.model.LoginContract.LoginEvent
import org.sopt.and.feature.login.model.LoginContract.LoginSideEffect
import org.sopt.and.feature.login.model.LoginContract.LoginState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : BaseViewModel<LoginState, LoginSideEffect, LoginEvent>() {

    override fun createInitialState() = LoginState()

    override suspend fun handleEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.UpdateEmail -> setState { copy(email = event.email) }
            is LoginEvent.UpdatePassword -> setState { copy(password = event.password) }
            is LoginEvent.SubmitLogin -> submitLogin(event.email, event.password)
        }
    }

    private fun submitLogin(email: String, password: String) {
        setState { copy(isLoading = true, errorMessage = null) }
        viewModelScope.launch {
            loginRepository.postLogin(LoginInfo(email, password))
                .onSuccess { response ->
                    setSideEffect { LoginSideEffect.NavigateToMyPageWithToken(response.token) }
                }
                .onFailure {
                    setSideEffect { LoginSideEffect.ShowSnackbar("로그인 실패") }
                }
        }
    }
}
