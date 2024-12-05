package org.sopt.and.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.UiState
import org.sopt.and.domain.repository.SignUpRepository
import org.sopt.and.domain.entity.UserNumber
import org.sopt.and.domain.entity.UserInfo
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _hobby = MutableStateFlow("")
    val hobby: StateFlow<String> = _hobby

    private val _signUpState = MutableStateFlow<UiState<UserNumber>>(UiState.Loading)
    val signUpState: StateFlow<UiState<UserNumber>> = _signUpState

    fun updateEmail(newEmail: String) {
        _signUpState.value = UiState.Loading
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _signUpState.value = UiState.Loading
        _password.value = newPassword
    }

    fun updateHobby(newHobby: String) {
        _signUpState.value = UiState.Loading
        _hobby.value = newHobby
    }

    fun submitSignUp(userInfo: UserInfo) {
        viewModelScope.launch {
            signUpRepository.postSignUp(userInfo)
                .onSuccess { response ->
                    _signUpState.emit(UiState.Success(response))
                }
                .onFailure { exception ->
                    val errorMessage = exception.message ?: "알 수 없는 오류"
                    _signUpState.emit(UiState.Failure(errorMessage))
                }
        }
    }

}
