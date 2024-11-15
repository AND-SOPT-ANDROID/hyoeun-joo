package org.sopt.and.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.UiState
import org.sopt.and.domain.repository.LoginRepository
import org.sopt.and.feature.model.LoginInfo
import org.sopt.and.feature.model.ResponseLoginModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _loginState = MutableStateFlow<UiState<ResponseLoginModel>>(UiState.Loading)
    val loginState: StateFlow<UiState<ResponseLoginModel>> = _loginState

    private val _authToken = MutableStateFlow<String?>(null)
    val authToken: StateFlow<String?> = _authToken

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun submitLogin(userInfo: LoginInfo) {
        viewModelScope.launch {
            loginRepository.postLogin(userInfo)
                .onSuccess { response ->
                    _loginState.emit(UiState.Success(response))
                    _authToken.emit(response.token)
                }
                .onFailure { exception ->
                    _loginState.emit(UiState.Failure("로그인 실패"))
                }
        }
    }
}
