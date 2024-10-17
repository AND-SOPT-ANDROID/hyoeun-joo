package org.sopt.and.feature.mypage

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sopt.and.feature.model.UserInfo

class MyPageViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    fun setEmail(userInfo: UserInfo) {
        _email.value = userInfo.id
    }
}
